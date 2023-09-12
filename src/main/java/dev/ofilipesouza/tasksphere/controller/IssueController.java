package dev.ofilipesouza.tasksphere.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping
@RestController
public class IssueController {

    private UserService userService;
    private IssueService issueService;

    public IssueController(UserService userService, IssueService issueService) {
        this.userService = userService;
        this.issueService = issueService;
    }

    public ResponseEntity<?> commentOnIssue(){
        return null;
    }

    public ResponseEntity<?> assignIssueToUser(){
        return null;
    }

}
