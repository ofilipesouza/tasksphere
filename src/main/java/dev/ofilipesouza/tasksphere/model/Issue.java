package dev.ofilipesouza.tasksphere.model;

import dev.ofilipesouza.tasksphere.enums.IssueStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@ToString
@Entity(name = "issues")
@NoArgsConstructor
public class Issue extends Commentable {

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
}
