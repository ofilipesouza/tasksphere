package dev.ofilipesouza.tasksphere.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import dev.ofilipesouza.tasksphere.model.Project;


public interface ProjectRepository extends JpaRepository<Project, UUID> {
    
    public List<Project> findByCreatedBy(UserDetails userDetails);

}
