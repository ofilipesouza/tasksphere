package dev.ofilipesouza.tasksphere.controller;

import dev.ofilipesouza.tasksphere.dto.IssueDTO;
import dev.ofilipesouza.tasksphere.model.Issue;
import dev.ofilipesouza.tasksphere.utils.SessionUtils;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("issues")
@RestController
public class IssueController {

    private final UserService userService;
    private final IssueService issueService;

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

    @GetMapping
    public ResponseEntity<?> getIssuesRelatedToUser(HttpSession session){
        String emailFromSession = SessionUtils.getEmailFromSession(session);

        List<Issue> issuesByReporterOrAssignee = issueService.getIssuesByReporterOrAssignee(userService.findByEmail(emailFromSession));

        List<IssueDTO> issueDTOS = IssueDTO.mapIssuesToIssueDTO(issuesByReporterOrAssignee);

        return ResponseEntity.ok(issueDTOS);
    }

}
