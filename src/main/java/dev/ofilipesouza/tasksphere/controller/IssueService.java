package dev.ofilipesouza.tasksphere.controller;

import dev.ofilipesouza.tasksphere.dto.IssueCreationDTO;
import dev.ofilipesouza.tasksphere.model.Issue;
import dev.ofilipesouza.tasksphere.model.Project;
import dev.ofilipesouza.tasksphere.model.User;

import java.util.List;

public interface IssueService {
    Issue creatIssue(IssueCreationDTO data, User user);
    void createIssueAndAddItToProject(IssueCreationDTO data, User user, Project project);

    List<Issue> getIssuesByReporterOrAssignee(User user);

}
