package dev.ofilipesouza.tasksphere.controller;

import dev.ofilipesouza.tasksphere.dto.IssueCreationDTO;
import dev.ofilipesouza.tasksphere.model.Issue;
import dev.ofilipesouza.tasksphere.model.Project;
import dev.ofilipesouza.tasksphere.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IssueService {
    Issue creatIssue(IssueCreationDTO data, User user);
    void createIssueAndAddItToProject(IssueCreationDTO data, User user, Project project);

    List<Issue> getIssuesByReporterOrAssignee(User user);

    void assignIssueToUser(Issue issue, User user);

    Optional<Issue> findIssueById(UUID issueId);
    void removeAssignment( Issue issue );
}
