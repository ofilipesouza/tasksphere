package dev.ofilipesouza.tasksphere.service;

import java.util.List;
import dev.ofilipesouza.tasksphere.model.Project;

public interface ProjectService {

    List<Project> getProjectsByUser(String email);
}
