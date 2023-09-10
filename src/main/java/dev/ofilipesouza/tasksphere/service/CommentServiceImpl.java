package dev.ofilipesouza.tasksphere.service;

import java.time.LocalDateTime;
import java.util.UUID;
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

    private Comment buildComment(CommentCreationDTO data, User user) {
        
        Comment comment = new Comment();
        comment.setComment(data.comment());
        comment.setCreatedBy(user);
        comment.setCreatedDate(LocalDateTime.now());
        return comment;
    }

    private Comment getComment(UUID commmentUuid){
        return commentRepository.findById(commmentUuid).orElseThrow(()-> new RuntimeException("Not Found"));
    }

    @Override
    public Commentable addComment(Commentable obj, CommentCreationDTO data, User user) {

        Comment comment = buildComment(data, user);

        commentRepository.save(comment);
        obj.addComment(comment);

        return obj;
    }

    @Override
    public Commentable deleteComment(Commentable obj, CommentCreationDTO data) {
        Comment comment = getComment(data.commentId());
        obj.deleteComment(comment);
        commentRepository.delete(comment);
        return obj;
    }

    @Override
    public Commentable editComment(Commentable obj, CommentCreationDTO data, User user) {
        Comment comment = getComment(data.commentId());
        comment.setComment(data.comment());
        commentRepository.save(comment);
        obj.editComment(comment);

        return obj;
        
    }

}
