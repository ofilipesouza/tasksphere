package dev.ofilipesouza.tasksphere.controller;

import java.util.UUID;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import dev.ofilipesouza.tasksphere.model.Comment;
import dev.ofilipesouza.tasksphere.model.Issue;
import dev.ofilipesouza.tasksphere.model.Project;
import dev.ofilipesouza.tasksphere.repository.IssueRepository;
import dev.ofilipesouza.tasksphere.repository.ProjectRepository;
@RequestMapping
public class CommentController {

    private final IssueRepository issueRepository;
    private final ProjectRepository projectRepository;

    public CommentController(IssueRepository issueRepository, ProjectRepository projectRepository) {
        this.issueRepository = issueRepository;
        this.projectRepository = projectRepository;
    }

	@PostMapping
	public Issue fuck(){
return new Issue();
	}


    @PostMapping("/issues/{issueId}/comments")
    public Issue addCommentToIssue(@PathVariable UUID issueId, @RequestBody Comment comment) throws NotFoundException {
        // Issue issue = issueRepository.findById(issueId).orElseThrow(NotFoundException::new);
        // issue.getComments().add(comment);
        // return issueRepository.save(issue);
        return null;
    }

    @PostMapping("/projects/{projectId}/comments")
    public Project addCommentToProject(@PathVariable UUID projectId, @RequestBody Comment comment) throws NotFoundException {
        // Project project = projectRepository.findById(projectId).orElseThrow(NotFoundException::new);
        // project.getComments().add(comment);
        // return projectRepository.save(project);
        return null;
    }
}
