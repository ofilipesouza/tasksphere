package dev.ofilipesouza.tasksphere.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import dev.ofilipesouza.tasksphere.model.Comment;
import jakarta.persistence.OneToMany;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public abstract class Commentable <T extends Commentable<T>> {
    
    private JpaRepository<T, UUID> repository;
    
    public Commentable(JpaRepository<T, UUID> repository) {
        this.repository = repository;
    }

    @OneToMany
    private List<Comment> comments = new ArrayList<>();

    public void addComment(Comment comment){
        this.comments.add(comment);
    };

    public void editComment(Comment comment){};
    public void deleteComment(Comment comment){}
}
