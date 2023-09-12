package dev.ofilipesouza.tasksphere.dto;

import dev.ofilipesouza.tasksphere.model.Project;
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
    private UserDTO createdBy;
    private LocalDateTime createdDate;
    private List<IssueDTO> issues;

    public static ProjectDTO mapProjectToProjectDTO(Project project){
        return new ProjectDTO(project.getId().toString(),
         project.getName(),
         project.getDescription(),
         new UserDTO(
            project.getCreatedBy().getFirstName(),
            project.getCreatedBy().getLastName(),
            project.getCreatedBy().getEmail(),
            project.getCreatedBy().getCreatedDate()),
         project.getCreatedDate(),
         IssueDTO.mapIssuesToIssueDTO(project.getIssues()));
    }

    public static List<ProjectDTO> mapProjectsToProjectsDTO(List<Project> projects){

        return projects.stream().map(
                        ProjectDTO::mapProjectToProjectDTO)
            .toList();
    }

}
