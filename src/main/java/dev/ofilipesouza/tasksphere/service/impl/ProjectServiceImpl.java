package dev.ofilipesouza.tasksphere.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;
import dev.ofilipesouza.tasksphere.controller.Commentable;
import dev.ofilipesouza.tasksphere.dto.CommentCreationDTO;
import dev.ofilipesouza.tasksphere.dto.ProjectCreationDTO;
import dev.ofilipesouza.tasksphere.model.Project;
import dev.ofilipesouza.tasksphere.model.User;
import dev.ofilipesouza.tasksphere.repository.ProjectRepository;
import dev.ofilipesouza.tasksphere.repository.UserRepository;
import dev.ofilipesouza.tasksphere.service.CommentService;
import dev.ofilipesouza.tasksphere.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final UserRepository userRepository;

    private final ProjectRepository projectRepository;

    private final CommentService commentService;

    public ProjectServiceImpl(UserRepository userRepository, ProjectRepository projectRepository, CommentService commentService) {
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
        this.commentService = commentService;
    }

    @Override
    public List<Project> getProjectsByUser(String email) {
        return this.projectRepository.findByCreatedBy(userRepository.findByEmail(email));
    }

    @Override
    public Optional<Project> getProjectById(UUID projectId) {
        return projectRepository.findById(projectId);
    }

    @Override
    public Project createProject(ProjectCreationDTO data, User user) {
        Project project = new Project(data.name(), data.description(), user);
        projectRepository.save(project);
        return project;
    }

    @Override
    public void addCommentToProject(Commentable obj, CommentCreationDTO data, User user){
        Project project = (Project) commentService.addComment(obj,data,user);
        projectRepository.save(project);
    }

    @Override
    public void editComment(Project project, CommentCreationDTO data, User user) {
        commentService.editComment(project,data,user);
        projectRepository.save(project);
    }

    @Override
    public void deleteComment(Project project, CommentCreationDTO data, User user) {
        commentService.deleteComment(project, data);
    }
    
}
