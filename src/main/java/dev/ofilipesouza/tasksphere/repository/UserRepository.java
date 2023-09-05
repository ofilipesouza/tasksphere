package dev.ofilipesouza.tasksphere.repository;

import dev.ofilipesouza.tasksphere.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
