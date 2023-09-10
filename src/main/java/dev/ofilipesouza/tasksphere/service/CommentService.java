package dev.ofilipesouza.tasksphere.service;

import dev.ofilipesouza.tasksphere.controller.Commentable;
import dev.ofilipesouza.tasksphere.model.Comment;

public interface CommentService {
    
    public void addComment(Commentable<?> obj, Comment comment);
    public void deleteCommentFromProject();
    public void editCommentOnProject();

}
