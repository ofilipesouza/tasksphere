package dev.ofilipesouza.tasksphere.model;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@NoArgsConstructor
@Getter
@MappedSuperclass
public abstract class Commentable {

    @OneToMany
    private Map<UUID, Comment> comments = new HashMap<>();

    public void addComment(Comment comment){
        this.comments.put(comment.getId(), comment);
    }

    public void editComment(Comment comment){
        this.comments.put(comment.getId(), comment);
    }
    public void deleteComment(Comment comment){
        this.comments.remove(comment.getId());
    }

}
