package dev.ofilipesouza.tasksphere.service;

import dev.ofilipesouza.tasksphere.controller.Commentable;
import dev.ofilipesouza.tasksphere.dto.CommentCreationDTO;
import dev.ofilipesouza.tasksphere.dto.ProjectCreationDTO;
import dev.ofilipesouza.tasksphere.model.Project;
import dev.ofilipesouza.tasksphere.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProjectService {

    List<Project> getProjectsByUser(String email);

    Optional<Project> getProjectById(UUID projectId);

    Project createProject(ProjectCreationDTO data, User user);

    void addCommentToProject( Commentable obj, CommentCreationDTO data, User user );

    void editComment(Project project, CommentCreationDTO data, UUID commentId, User user);

    void deleteComment(Project project, UUID commentId);

}
