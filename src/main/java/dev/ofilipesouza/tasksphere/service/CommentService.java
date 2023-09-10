package dev.ofilipesouza.tasksphere.service;

import dev.ofilipesouza.tasksphere.controller.Commentable;
import dev.ofilipesouza.tasksphere.dto.CommentCreationDTO;
import dev.ofilipesouza.tasksphere.model.User;

public interface CommentService {
    public Commentable addComment(Commentable obj, CommentCreationDTO data, User user);
    public Commentable deleteComment(Commentable obj, CommentCreationDTO data);
    public Commentable editComment(Commentable obj, CommentCreationDTO data, User user);

}
