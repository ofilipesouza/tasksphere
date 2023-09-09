package dev.ofilipesouza.tasksphere.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
    @ManyToOne
    private User createdBy;
    private LocalDateTime createdDate;
    @OneToMany
    private List<Issue> issues;
    @OneToMany
    private List<Comment> comments;

    public Project(String name, String description, User createdByUser){
        this.name = name;
        this.description = description;
        this.createdDate = LocalDateTime.now();
        this.createdBy = createdByUser;
    }
}
