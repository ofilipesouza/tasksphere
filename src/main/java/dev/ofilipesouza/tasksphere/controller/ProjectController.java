package dev.ofilipesouza.tasksphere.controller;

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
import dev.ofilipesouza.tasksphere.dto.ProjectCreationDTO;
import dev.ofilipesouza.tasksphere.dto.ProjectDTO;
import dev.ofilipesouza.tasksphere.exception.ErrorResponse;
import dev.ofilipesouza.tasksphere.model.Project;
import dev.ofilipesouza.tasksphere.model.User;
import dev.ofilipesouza.tasksphere.repository.ProjectRepository;
import dev.ofilipesouza.tasksphere.repository.UserRepository;
import dev.ofilipesouza.tasksphere.service.ProjectService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;


@RestController
@RequestMapping("project")
public class ProjectController {

    @Autowired
    private final ProjectService projectService;

    @Autowired
    private final ProjectRepository projectRepository;

    @Autowired
    private final UserRepository userRepository;

    public ProjectController(ProjectService projectService, ProjectRepository projectRepository, UserRepository userRepository) {
        this.projectService = projectService;
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<ProjectDTO> createProject(@RequestBody @Valid ProjectCreationDTO data, HttpSession session){
        User user = (User) userRepository.findByEmail(session.getAttribute("username").toString());
        Project project = new Project(data.name(), data.description(), user);
        projectRepository.save(project);
        return ResponseEntity.ok(ProjectDTO.mapProjectToProjectDTO(project));
    }

    @GetMapping
    public ResponseEntity<List<ProjectDTO>> getProjectsByUser(HttpSession httpSession){
        List<Project> projectsByUser = projectService.getProjectsByUser(httpSession.getAttribute("username").toString());
        return ResponseEntity.ok(ProjectDTO.mapProjectsToProjectsDTO(projectsByUser));
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<?> getProjectById(@PathVariable @Valid UUID projectId) throws NotFoundException{
        Optional<Project> findById = projectRepository.findById(projectId);
        
        if(findById.isPresent()){
            return ResponseEntity.ok(ProjectDTO.mapProjectToProjectDTO(findById.get()));
        }
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("Project not Found!🙁"));
    }
}
