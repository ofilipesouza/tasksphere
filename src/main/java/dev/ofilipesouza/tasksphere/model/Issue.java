package dev.ofilipesouza.tasksphere.model;

import dev.ofilipesouza.tasksphere.enums.IssueStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
@Entity(name = "issues")
@NoArgsConstructor
public class Issue {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String title;
    private String description;
    private IssueStatus status;
    @ManyToOne
    @JoinColumn(name = "assignee_id")
    private User assignee;
    @ManyToOne
    @JoinColumn(name = "reporter_id")
    private User reporter;
    @OneToMany
    private List<Comment> comments;
}
