package dev.ofilipesouza.tasksphere.controller;

import dev.ofilipesouza.tasksphere.dto.ProjectDTO;
import dev.ofilipesouza.tasksphere.model.Issue;
import dev.ofilipesouza.tasksphere.model.Project;
import dev.ofilipesouza.tasksphere.service.ProjectService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
public class ViewController {

    ProjectService projectService;
    UserService userService;

    private final String PROJECT = "project";
    private final String DASHBOARD = "dashboard";
    private final String LOGIN = "login";

    public ViewController( ProjectService projectService ) {
        this.projectService = projectService;
    }

    @GetMapping("/")
    public String index( HttpSession session ) {
        return DASHBOARD;
    }

    @GetMapping("dashboard")
    public ModelAndView dashboard( HttpSession httpSession ) {
        HashMap<String, List<ProjectDTO>> model = new HashMap<>();
        var projectsByUser = ProjectDTO.mapProjectsToProjectsDTO( projectService.getProjectsByUser( httpSession.getAttribute( "username" ).toString() ) );
        model.put( "projects", projectsByUser );
        return new ModelAndView( DASHBOARD, model );
    }

    @GetMapping(value = "project/{projectId}", headers = "HX-Redirect")
    public ModelAndView project( @PathVariable UUID projectId ) {
        Optional<Project> projectById = projectService.getProjectById( projectId );
        HashMap<String, List<Issue>> model = new HashMap<>();
        if (projectById.isPresent()) {
            Project project = projectById.get();
            model.put( "issues", project.getIssues() );
        }
        return new ModelAndView( PROJECT, model );
    }

    @GetMapping("public/login")
    public String login() {
        return LOGIN;
    }
}
