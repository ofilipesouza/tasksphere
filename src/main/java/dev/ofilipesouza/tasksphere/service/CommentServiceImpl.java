package dev.ofilipesouza.tasksphere.service;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import dev.ofilipesouza.tasksphere.controller.Commentable;
import dev.ofilipesouza.tasksphere.dto.CommentCreationDTO;
import dev.ofilipesouza.tasksphere.model.Comment;
import dev.ofilipesouza.tasksphere.model.User;
import dev.ofilipesouza.tasksphere.repository.CommentRepository;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public void handleComment(Commentable<?> obj, CommentCreationDTO data, User user) {
        
        Comment comment = buildComment(data, user);

        switch (data.action()) {
            case ADD:
                addComment(obj, comment);
                break;
        
            case EDIT:
                
                break;

            case DELETE:

                break;
            default:
                break;
        }
        
    }

    private Comment buildComment(CommentCreationDTO data, User user) {
        
        Comment comment = new Comment();
        comment.setComment(data.comment());
        comment.setCreatedBy(user);
        comment.setCreatedDate(LocalDateTime.now());
        return comment;
    }

    @Override
    public void addComment(Commentable<?> obj, Comment comment) {
        commentRepository.save(comment);
        obj.addComment(comment);
    }

    @Override
    public void deleteComment(Commentable<?> obj, Comment comment) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteComment'");
    }

    @Override
    public void editComment(Commentable<?> obj, Comment comment) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'editComment'");
    }
    
}
