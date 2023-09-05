package dev.ofilipesouza.tasksphere.controller;

import dev.ofilipesouza.tasksphere.service.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public class ProjectController extends TaskSphereController{

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/projects")
    public ResponseEntity<String> getProjectsByUser(@RequestHeader("username") String username){
        projectService.equals("");
        return ResponseEntity.ok(username);
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<String> getProjectById(@PathVariable String projectId){
        return ResponseEntity.ok(projectId);
    }
}
