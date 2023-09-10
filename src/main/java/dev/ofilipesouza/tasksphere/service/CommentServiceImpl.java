package dev.ofilipesouza.tasksphere.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import dev.ofilipesouza.tasksphere.controller.Commentable;
import dev.ofilipesouza.tasksphere.model.Comment;
import dev.ofilipesouza.tasksphere.repository.CommentRepository;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentRepository commentRepository;


    @Override
    public void deleteCommentFromProject() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteCommentFromProject'");
    }

    @Override
    public void editCommentOnProject() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'editCommentOnProject'");
    }

    @Override
    public void addComment(Commentable<?> obj, Comment comment) {
        commentRepository.save(comment);
        obj.addComment(comment);
    }
    
}
