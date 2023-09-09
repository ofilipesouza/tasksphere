package dev.ofilipesouza.tasksphere.dto;

import java.time.LocalDateTime;
import java.util.List;
import dev.ofilipesouza.tasksphere.model.Issue;
import dev.ofilipesouza.tasksphere.model.Project;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDTO {
    private String id;
    private String name;
    private String description;
    private UserDTO createdBy;
    private LocalDateTime createdDate;
    private List<Issue> issues;

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
         project.getIssues());
    }

    public static List<ProjectDTO> mapProjectsToProjectsDTO(List<Project> projects){

        List<ProjectDTO> listDtos = projects.stream().map(
            project -> mapProjectToProjectDTO(project))
            .toList();

        return listDtos;
    }
    
}
