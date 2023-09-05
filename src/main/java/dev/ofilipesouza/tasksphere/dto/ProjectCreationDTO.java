package dev.ofilipesouza.tasksphere.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectCreationDTO {
    private String name;
    private String description;
    private String createdByUserId;
}
