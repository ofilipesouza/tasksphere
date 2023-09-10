package dev.ofilipesouza.tasksphere.dto;

import dev.ofilipesouza.tasksphere.enums.CommentAction;

public record CommentCreationDTO(String comment, CommentAction action) {

}
