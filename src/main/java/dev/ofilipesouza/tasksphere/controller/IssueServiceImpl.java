package dev.ofilipesouza.tasksphere.controller;

import dev.ofilipesouza.tasksphere.dto.IssueCreationDTO;
import dev.ofilipesouza.tasksphere.enums.IssueStatus;
import dev.ofilipesouza.tasksphere.model.Issue;
import dev.ofilipesouza.tasksphere.model.Project;
import dev.ofilipesouza.tasksphere.model.User;
import dev.ofilipesouza.tasksphere.repository.IssueRepository;
import dev.ofilipesouza.tasksphere.repository.ProjectRepository;
import dev.ofilipesouza.tasksphere.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class IssueServiceImpl implements IssueService{

    private final IssueRepository issueRepository;
    private final ProjectRepository projectRepository;

    private final UserRepository userRepository;

    public IssueServiceImpl(IssueRepository issueRepository, ProjectRepository projectRepository, UserRepository userRepository) {
        this.issueRepository = issueRepository;
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
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

    @Override
    public void assignIssueToUser(Issue issue, User user) {
        issue.setAssignee( user );
        userRepository.save( user );
    }

    @Override
    public Optional<Issue> findIssueById(UUID issueId) {
        return issueRepository.findById(issueId);
    }

}
