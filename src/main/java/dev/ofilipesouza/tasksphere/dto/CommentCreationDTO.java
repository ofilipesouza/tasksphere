package dev.ofilipesouza.tasksphere.dto;

import java.util.UUID;
import dev.ofilipesouza.tasksphere.enums.CommentAction;

public record CommentCreationDTO(String comment, CommentAction action, UUID commentId) {

}
