package dev.ofilipesouza.tasksphere.repository;

import dev.ofilipesouza.tasksphere.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.UUID;


public interface ProjectRepository extends JpaRepository<Project, UUID> {

}
