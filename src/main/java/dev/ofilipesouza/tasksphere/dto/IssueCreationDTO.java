package dev.ofilipesouza.tasksphere.dto;

import java.util.UUID;

public record IssueCreationDTO(UUID projectId, String title, String description) {
}
