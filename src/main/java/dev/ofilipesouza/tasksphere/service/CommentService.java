package dev.ofilipesouza.tasksphere.service;

import dev.ofilipesouza.tasksphere.controller.Commentable;
import dev.ofilipesouza.tasksphere.dto.CommentCreationDTO;
import dev.ofilipesouza.tasksphere.model.Comment;
import dev.ofilipesouza.tasksphere.model.User;

public interface CommentService {
    public void handleComment(Commentable<?> obj, CommentCreationDTO data, User user);
    public void addComment(Commentable<?> obj, Comment comment);
    public void deleteComment(Commentable<?> obj, Comment comment);
    public void editComment(Commentable<?> obj, Comment comment);

}
