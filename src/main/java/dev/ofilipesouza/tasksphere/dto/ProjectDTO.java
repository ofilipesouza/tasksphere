package dev.ofilipesouza.tasksphere.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDTO {
    private String id;
    private String name;
    private String description;
    private String createdByUserId;
    private LocalDateTime createdDate;
    private List<String> issueIds;
}
