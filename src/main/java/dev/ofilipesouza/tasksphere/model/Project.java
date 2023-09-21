package dev.ofilipesouza.tasksphere.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity(name = "projects")
public class Project extends Commentable{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String description;
    @ManyToOne
    private User createdBy;
    private LocalDateTime createdDate;
    @OneToMany
    private List<Issue> issues = new ArrayList<>();

    public Project(String name, String description, User createdByUser){
        this.name = name;
        this.description = description;
        this.createdDate = LocalDateTime.now();
        this.createdBy = createdByUser;
    }

}
