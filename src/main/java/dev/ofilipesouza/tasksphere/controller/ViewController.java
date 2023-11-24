package dev.ofilipesouza.tasksphere.controller;

import dev.ofilipesouza.tasksphere.dto.IssueDTO;
import dev.ofilipesouza.tasksphere.dto.ProjectDTO;
import dev.ofilipesouza.tasksphere.model.Project;
import dev.ofilipesouza.tasksphere.service.ProjectService;
import dev.ofilipesouza.tasksphere.utils.SessionUtils;
import jakarta.servlet.http.HttpServletResponse;
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

    @GetMapping(value = "/project/{projectId}")
    public ModelAndView project( @PathVariable UUID projectId, HttpServletResponse response, HttpSession session ) {
        Optional<Project> projectById = projectService.getProjectById( projectId );
        var projects = projectService.getProjectsByUser( SessionUtils.getEmailFromSession( session ) );
        HashMap<String, List<?>> model = new HashMap<>();
        if (projectById.isPresent()) {
            Project project = projectById.get();
            model.put( "issues", IssueDTO.mapIssuesToIssueDTO( project.getIssues() ) );
        }
		System.out.println("Testando");
        model.put( "projects", ProjectDTO.mapProjectsToProjectsDTO( projects ) );
        response.addHeader( "HX-Redirect", "/project/" + projectId );
        return new ModelAndView( "project", model );

    }

    @GetMapping(value = "public/login")
    public String login() {
        return LOGIN;
    }
}
