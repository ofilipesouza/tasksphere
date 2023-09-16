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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("project")
public class ProjectController {
    private final ProjectService projectService;
    private final UserService userService;
    private final IssueService issueService;

    private final String PROJECT_NOT_FOUND = "Project not Found! 🙁";

    public ProjectController( ProjectService projectService, UserService userService,
                              IssueService issueService ) {
        this.projectService = projectService;
        this.userService = userService;
        this.issueService = issueService;
    }

    @PostMapping
    public ResponseEntity<ProjectDTO> createProject( @RequestBody @Valid ProjectCreationDTO data,
                                                     HttpSession session ) {
        String email = SessionUtils.getEmailFromSession( session );
        User user = userService.findByEmail( email );
        Project createProject = projectService.createProject( data, user );
        return ResponseEntity.ok( ProjectDTO.mapProjectToProjectDTO( createProject ) );
    }

    @GetMapping
    public ResponseEntity<List<ProjectDTO>> getProjectsByUser( HttpSession httpSession ) {
        List<Project> projectsByUser =
                projectService.getProjectsByUser( httpSession.getAttribute( "username" ).toString() );
        return ResponseEntity.ok( ProjectDTO.mapProjectsToProjectsDTO( projectsByUser ) );
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<?> getProjectById( @PathVariable @Valid UUID projectId ) {

        Optional<Project> projectById = projectService.getProjectById( projectId );

        if (projectById.isPresent()) {
            return ResponseEntity.ok( ProjectDTO.mapProjectToProjectDTO( projectById.get() ) );
        }

        return ResponseEntity.status( HttpStatus.NOT_FOUND )
                .body( new ErrorResponse( PROJECT_NOT_FOUND ) );
    }

    @PostMapping("/{projectId}/issue")
    public ResponseEntity<?> addIssueToProject( @PathVariable @Valid UUID projectId,
                                                @RequestBody IssueCreationDTO data, HttpSession session ) {

        Optional<Project> projectById = projectService.getProjectById( projectId );

        if (projectById.isPresent()) {
            String email = SessionUtils.getEmailFromSession( session );
            User user = userService.findByEmail( email );
            issueService.createIssueAndAddItToProject( data, user, projectById.get() );
            return ResponseEntity.ok( "Issue created! 📌" );
        }

        return ResponseEntity.status( HttpStatus.NOT_FOUND )
                .body( new ErrorResponse( PROJECT_NOT_FOUND ) );
    }

    @PostMapping("/{projectId}/comment/{commentId}")
    public ResponseEntity<?> commentOnProject( @PathVariable @Valid UUID projectId, @PathVariable @Valid UUID commentId,
                                               @RequestBody CommentCreationDTO data, HttpSession session ) {

        Optional<Project> projectById = projectService.getProjectById( projectId );

        if (projectById.isPresent()) {
            String email = SessionUtils.getEmailFromSession( session );

            User user = userService.findByEmail( email );

            if (commentId != null) {
                projectService.editComment( projectById.get(), data, commentId, user );
            } else {
                projectService.addCommentToProject( projectById.get(), data, user );
            }

            return ResponseEntity.ok( "Comment created! 📝" );
        }

        return ResponseEntity.ok( data );
    }

    @DeleteMapping("/{projectId}/comment/{commentId}")
    public ResponseEntity<?> deleteComment( @PathVariable @Valid UUID projectId, @PathVariable UUID commentId ) {

        Optional<Project> projectById = projectService.getProjectById( projectId );

        projectById.ifPresent( project -> projectService.deleteComment( project, commentId ) );


        return ResponseEntity.noContent().build();
    }
}
