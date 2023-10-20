package dev.ofilipesouza.tasksphere.dto;


import dev.ofilipesouza.tasksphere.enums.IssueStatus;
import dev.ofilipesouza.tasksphere.model.Issue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class IssueDTO {

    private UUID id;
    private String title;
    private String description;
    private IssueStatus status;
    private UserDTO assignee;
    private UserDTO reporter;

    public static IssueDTO mapIssueToIssueDTO(Issue issue){

        var issueDTO = new IssueDTO();
        issueDTO.id = issue.getId();
        issueDTO.title = issue.getTitle();
        issueDTO.description = issue.getDescription();
        issueDTO.status = issue.getStatus();
        issueDTO.assignee = UserDTO.mapUserToUserDTO(issue.getAssignee());
        issueDTO.reporter = UserDTO.mapUserToUserDTO(issue.getReporter());

        return issueDTO;

    }

    public static List<IssueDTO> mapIssuesToIssueDTO(List<Issue> issues){

        return  issues.stream().map(IssueDTO::mapIssueToIssueDTO).toList();

    }
}
