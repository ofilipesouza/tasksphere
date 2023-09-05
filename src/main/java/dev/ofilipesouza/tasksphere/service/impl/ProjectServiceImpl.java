package dev.ofilipesouza.tasksphere.service.impl;

import org.springframework.stereotype.Service;

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
}
