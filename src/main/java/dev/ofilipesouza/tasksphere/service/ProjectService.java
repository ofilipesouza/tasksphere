package dev.ofilipesouza.tasksphere.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import dev.ofilipesouza.tasksphere.dto.ProjectCreationDTO;
import dev.ofilipesouza.tasksphere.model.Project;
import dev.ofilipesouza.tasksphere.model.User;

public interface ProjectService {

    List<Project> getProjectsByUser(String email);

    Optional<Project> getProjectById(UUID projectId);

    Project createProject(ProjectCreationDTO data, User user);

}
