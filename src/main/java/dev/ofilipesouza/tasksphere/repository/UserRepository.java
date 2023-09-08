package dev.ofilipesouza.tasksphere.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import dev.ofilipesouza.tasksphere.model.User;

public interface UserRepository extends JpaRepository<User, UUID> {

    public User findByEmailAndPassword(String email, String password);

    public UserDetails findByEmail(String username);
}
