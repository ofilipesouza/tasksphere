package dev.ofilipesouza.tasksphere.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import dev.ofilipesouza.tasksphere.service.ProjectService;


@RestController
@RequestMapping("project")
public class ProjectController {

    @Autowired
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public ResponseEntity<String> getProjectsByUser(){
        return ResponseEntity.ok("Teste");
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<String> getProjectById(@PathVariable String projectId){
        return ResponseEntity.ok(projectId);
    }
}
