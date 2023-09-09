package dev.ofilipesouza.tasksphere.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import dev.ofilipesouza.tasksphere.model.Project;
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

    
}
