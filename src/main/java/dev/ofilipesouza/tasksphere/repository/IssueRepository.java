package dev.ofilipesouza.tasksphere.repository;

import dev.ofilipesouza.tasksphere.model.Issue;
import dev.ofilipesouza.tasksphere.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;


public interface IssueRepository extends JpaRepository<Issue, UUID> {

    List<Issue> findByAssigneeOrReporter(User assignee, User reporter);
}
