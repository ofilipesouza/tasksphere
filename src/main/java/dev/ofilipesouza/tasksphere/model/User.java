package dev.ofilipesouza.tasksphere.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
@Entity(name = "users")
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(unique = true)
    private UUID id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private LocalDateTime createdDate;
    @OneToMany
    private List<Issue> assignedIssues;
    @OneToMany
    private List<Issue> reportedIssues;

}
