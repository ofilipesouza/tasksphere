package dev.ofilipesouza.tasksphere.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String description;
    @OneToOne
    private User createdBy;
    private LocalDateTime createdDate;
    @OneToMany
    private List<Issue> issues;
    @OneToMany
    private List<Comment> comments;
}
