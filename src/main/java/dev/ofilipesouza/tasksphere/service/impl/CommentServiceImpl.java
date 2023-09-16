package dev.ofilipesouza.tasksphere.service.impl;

import dev.ofilipesouza.tasksphere.controller.Commentable;
import dev.ofilipesouza.tasksphere.dto.CommentCreationDTO;
import dev.ofilipesouza.tasksphere.model.Comment;
import dev.ofilipesouza.tasksphere.model.User;
import dev.ofilipesouza.tasksphere.repository.CommentRepository;
import dev.ofilipesouza.tasksphere.service.CommentService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

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
    public void deleteComment( Commentable obj, UUID commentId) {
        Comment comment = getComment(commentId);
        obj.deleteComment(comment);
        commentRepository.delete(comment);
    }

    @Override
    public void editComment( Commentable obj, CommentCreationDTO data, UUID commentId, User user) {
        Comment comment = getComment(commentId);
        comment.setComment(data.comment());
        commentRepository.save(comment);
        obj.editComment(comment);

    }

}
