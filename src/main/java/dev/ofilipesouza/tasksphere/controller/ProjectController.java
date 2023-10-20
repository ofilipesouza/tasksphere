package dev.ofilipesouza.tasksphere.controller;

import dev.ofilipesouza.tasksphere.dto.CommentCreationDTO;
import dev.ofilipesouza.tasksphere.dto.IssueCreationDTO;
import dev.ofilipesouza.tasksphere.dto.ProjectCreationDTO;
import dev.ofilipesouza.tasksphere.dto.ProjectDTO;
import dev.ofilipesouza.tasksphere.exception.ErrorResponse;
import dev.ofilipesouza.tasksphere.model.Project;
import dev.ofilipesouza.tasksphere.model.User;
import dev.ofilipesouza.tasksphere.service.ProjectService;
import dev.ofilipesouza.tasksphere.utils.SessionUtils;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("api/v1/project")
public class ProjectController {
    private final ProjectService projectService;
    private final UserService userService;
    private final IssueService issueService;

    @Autowired
    TemplateEngine templateEngine;

    private final String PROJECT_NOT_FOUND = "Project not Found! 🙁";

    public ProjectController(ProjectService projectService, UserService userService,
                             IssueService issueService) {
        this.projectService = projectService;
        this.userService = userService;
        this.issueService = issueService;
    }

    @PostMapping
    public ResponseEntity<?> createProject(@RequestBody @Valid ProjectCreationDTO data,
                                           HttpSession session) {
        Context context = new Context();
        String email = SessionUtils.getEmailFromSession(session);
        User user = userService.findByEmail(email);
        Project createProject = projectService.createProject(data, user);
        context.setVariable("project", createProject);
        HashMap<String, List<ProjectDTO>> model = new HashMap<>();
        var html = templateEngine.process("fragments/project-nav-item", context);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "text/html");
        return new ResponseEntity<>(html, headers, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ProjectDTO>> getProjectsByUser(HttpSession httpSession) {
        List<Project> projectsByUser =
                projectService.getProjectsByUser(httpSession.getAttribute("username").toString());
        return ResponseEntity.ok(ProjectDTO.mapProjectsToProjectsDTO(projectsByUser));
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<?> getProjectById(@PathVariable @Valid UUID projectId) {

        Optional<Project> projectById = projectService.getProjectById(projectId);

        if (projectById.isPresent()) {
            return ResponseEntity.ok(ProjectDTO.mapProjectToProjectDTO(projectById.get()));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(PROJECT_NOT_FOUND));
    }

    @PostMapping("/issue")
    public ResponseEntity<?> addIssueToProject(
            @RequestBody IssueCreationDTO data, HttpSession session) {

        Optional<Project> projectById = projectService.getProjectById(data.projectId());

        if (projectById.isPresent()) {
            String email = SessionUtils.getEmailFromSession(session);
            User user = userService.findByEmail(email);
            issueService.createIssueAndAddItToProject(data, user, projectById.get());
            return ResponseEntity.ok("Issue created! 📌");
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(PROJECT_NOT_FOUND));
    }

    @PostMapping("/{projectId}/comment/{commentId}")
    public ResponseEntity<?> commentOnProject(@PathVariable @Valid UUID projectId, @PathVariable @Valid UUID commentId,
                                              @RequestBody CommentCreationDTO data, HttpSession session) {

        Optional<Project> projectById = projectService.getProjectById(projectId);

        if (projectById.isPresent()) {
            String email = SessionUtils.getEmailFromSession(session);
            User user = userService.findByEmail(email);
            projectService.addCommentToProject(projectById.get(), data, user);
            return ResponseEntity.ok("Comment created! 📝");
        }

        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{projectId}/comment/{commentId}")
    public ResponseEntity<?> editCommentOnProject(@PathVariable @Valid UUID projectId, @PathVariable @Valid UUID commentId,
                                                  @RequestBody CommentCreationDTO data, HttpSession session) {
        Optional<Project> projectById = projectService.getProjectById(projectId);

        if (projectById.isPresent()) {
            String email = SessionUtils.getEmailFromSession(session);
            User user = userService.findByEmail(email);
            projectService.editComment(projectById.get(), data, commentId, user);
            return ResponseEntity.ok("Comment edited! 📝");
        }

        return ResponseEntity.badRequest().build();
    }


    @DeleteMapping("/{projectId}/comment/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable @Valid UUID projectId, @PathVariable UUID commentId) {
        Optional<Project> projectById = projectService.getProjectById(projectId);
        projectById.ifPresent(project -> projectService.deleteComment(project, commentId));
        return ResponseEntity.noContent().build();
    }
}
