package dev.ofilipesouza.tasksphere.repository;

import dev.ofilipesouza.tasksphere.model.Issue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface IssueRepository extends JpaRepository<Issue, UUID> {
}