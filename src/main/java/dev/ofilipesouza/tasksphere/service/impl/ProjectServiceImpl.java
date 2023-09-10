package dev.ofilipesouza.tasksphere.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;
import dev.ofilipesouza.tasksphere.dto.ProjectCreationDTO;
import dev.ofilipesouza.tasksphere.model.Project;
import dev.ofilipesouza.tasksphere.model.User;
import dev.ofilipesouza.tasksphere.repository.ProjectRepository;
import dev.ofilipesouza.tasksphere.repository.UserRepository;
import dev.ofilipesouza.tasksphere.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final UserRepository userRepository;

    private final ProjectRepository projectRepository;

    public ProjectServiceImpl(UserRepository userRepository, ProjectRepository projectRepository) {
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
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
    
}
