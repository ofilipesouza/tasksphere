package dev.ofilipesouza.tasksphere.controller;

import dev.ofilipesouza.tasksphere.dto.IssueCreationDTO;
import dev.ofilipesouza.tasksphere.enums.IssueStatus;
import dev.ofilipesouza.tasksphere.model.Issue;
import dev.ofilipesouza.tasksphere.model.Project;
import dev.ofilipesouza.tasksphere.model.User;
import dev.ofilipesouza.tasksphere.repository.IssueRepository;
import dev.ofilipesouza.tasksphere.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IssueServiceImpl implements IssueService{

    private final IssueRepository issueRepository;
    private final ProjectRepository projectRepository;

    public IssueServiceImpl(IssueRepository issueRepository, ProjectRepository projectRepository) {
        this.issueRepository = issueRepository;
        this.projectRepository = projectRepository;
    }

    @Override
    public Issue creatIssue(IssueCreationDTO data, User user) {

        Issue issue = new Issue();
        issue.setTitle(data.title());
        issue.setDescription(data.description());
        issue.setReporter(user);
        issue.setStatus(IssueStatus.OPEN);

        return issue;
    }

    public void createIssueAndAddItToProject(IssueCreationDTO data, User user, Project project){

        Issue issueCreated = creatIssue(data, user);

        project.getIssues().add(issueCreated);
        issueRepository.save(issueCreated);
        projectRepository.save(project);

    }

    public List<Issue> getIssuesByReporterOrAssignee(User user){
        return issueRepository.findByAssigneeOrReporter(user, user);
    }

}
