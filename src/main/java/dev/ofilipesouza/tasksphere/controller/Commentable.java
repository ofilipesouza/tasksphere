package dev.ofilipesouza.tasksphere.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import dev.ofilipesouza.tasksphere.model.Comment;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@MappedSuperclass
public abstract class Commentable {
 
    @OneToMany
    private Map<UUID, Comment> comments = new HashMap<UUID,Comment>();

    public void addComment(Comment comment){
        this.comments.put(comment.getId(), comment);
    };

    public void editComment(Comment comment){
        this.comments.put(comment.getId(), comment);
    };
    public void deleteComment(Comment comment){
        this.comments.remove(comment.getId());
    }
    
}
