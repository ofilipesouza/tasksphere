package dev.ofilipesouza.tasksphere.dto;

import java.time.LocalDateTime;


public record UserDTO(String firstName, String lastName, String email, LocalDateTime createdAt) {
}
