package dev.ofilipesouza.tasksphere.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String comment;
    @ManyToOne
    @JoinColumn(name = "created_by_id")
    private User createdBy;
    private LocalDateTime createdDate;
}
