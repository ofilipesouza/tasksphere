package dev.ofilipesouza.tasksphere.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping
@RestController
public class IssueController {

    @Autowired
    private UserService userService;

    @Autowired
    private IssueService issueService;

    public ResponseEntity<?> commentOnIssue(){
        return null;
    }

    public ResponseEntity<?> assignIssueToUser(){
        return null;
    }

}
