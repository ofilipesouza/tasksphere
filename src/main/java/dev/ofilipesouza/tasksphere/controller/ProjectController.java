package dev.ofilipesouza.tasksphere.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import dev.ofilipesouza.tasksphere.dto.CommentCreationDTO;
import dev.ofilipesouza.tasksphere.dto.IssueCreationDTO;
import dev.ofilipesouza.tasksphere.dto.ProjectCreationDTO;
import dev.ofilipesouza.tasksphere.dto.ProjectDTO;
import dev.ofilipesouza.tasksphere.exception.ErrorResponse;
import dev.ofilipesouza.tasksphere.model.Comment;
import dev.ofilipesouza.tasksphere.model.Project;
import dev.ofilipesouza.tasksphere.model.User;
import dev.ofilipesouza.tasksphere.service.CommentService;
import dev.ofilipesouza.tasksphere.service.ProjectService;
import dev.ofilipesouza.tasksphere.utils.SessionUtils;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;


@RestController
@RequestMapping("project")
public class ProjectController {

    @Autowired
    private final ProjectService projectService;

    @Autowired
    private final UserService userService;

    @Autowired 
    private final IssueService issueService;

    @Autowired
    private final CommentService commentService;

    private final String PROJECT_NOT_FOUND = "Project not Found! 🙁";

    public ProjectController(ProjectService projectService, UserService userService, IssueService issueService, CommentService commentService) {
        this.projectService = projectService;
        this.userService = userService;
        this.issueService = issueService;
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<ProjectDTO> createProject(@RequestBody @Valid ProjectCreationDTO data, HttpSession session){
        String email = SessionUtils.getEmailFromSession(session);
        User user = userService.findByEmail(email);
        Project createProject = projectService.createProject(data, user);
        return ResponseEntity.ok(ProjectDTO.mapProjectToProjectDTO(createProject));   
    }

    @GetMapping
    public ResponseEntity<List<ProjectDTO>> getProjectsByUser(HttpSession httpSession){
        List<Project> projectsByUser = projectService.getProjectsByUser(httpSession.getAttribute("username").toString());
        return ResponseEntity.ok(ProjectDTO.mapProjectsToProjectsDTO(projectsByUser));
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<?> getProjectById(@PathVariable @Valid UUID projectId) throws NotFoundException{
        
        Optional<Project> projectById = projectService.getProjectById(projectId);
        
        if(projectById.isPresent()){
            return ResponseEntity.ok(ProjectDTO.mapProjectToProjectDTO(projectById.get()));
        }
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(PROJECT_NOT_FOUND));
    }

    @PostMapping("/{projectId}/issue")
    public ResponseEntity<?> addIssueToProject(@PathVariable @Valid UUID projectId, @RequestBody IssueCreationDTO data, HttpSession session){

        Optional<Project> projectById = projectService.getProjectById(projectId);

        if(projectById.isPresent()){
            String email = SessionUtils.getEmailFromSession(session);
            User user = userService.findByEmail(email);
            issueService.createIssueAndAddItToProject(data, user, projectById.get());
            return ResponseEntity.ok("Issue created! 📌");
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(PROJECT_NOT_FOUND));
    }

    @PostMapping("/{projectId}/comment")
    public ResponseEntity<?> commentOnProject(@PathVariable @Valid UUID projectId, @RequestBody CommentCreationDTO data, HttpSession session){

        Optional<Project> projectById = projectService.getProjectById(projectId);

        if(projectById.isPresent()){
            String email = SessionUtils.getEmailFromSession(session);
            User user = userService.findByEmail(email);
            Comment comment = new Comment();
            comment.setComment(data.comment());
            comment.setCreatedBy(user);
            comment.setCreatedDate(LocalDateTime.now());
            commentService.addComment(projectById.get(), comment);
            return ResponseEntity.ok("Issue created! 📌");
        }
        
        return ResponseEntity.ok(data);
    }
}
