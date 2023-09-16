package dev.ofilipesouza.tasksphere.service;

import dev.ofilipesouza.tasksphere.controller.Commentable;
import dev.ofilipesouza.tasksphere.dto.CommentCreationDTO;
import dev.ofilipesouza.tasksphere.model.User;

import java.util.UUID;

public interface CommentService {
    Commentable addComment( Commentable obj, CommentCreationDTO data, User user );
    void deleteComment( Commentable obj, UUID commentId );
    void editComment( Commentable obj, CommentCreationDTO data, UUID commentId, User user );

}
