package dev.ofilipesouza.tasksphere.controller;

import dev.ofilipesouza.tasksphere.dto.AssignIssueDTO;
import dev.ofilipesouza.tasksphere.dto.IssueDTO;
import dev.ofilipesouza.tasksphere.model.Issue;
import dev.ofilipesouza.tasksphere.utils.SessionUtils;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("issues")
@RestController
public class IssueController {

    private final UserService userService;
    private final IssueService issueService;

    public IssueController( UserService userService, IssueService issueService ) {
        this.userService = userService;
        this.issueService = issueService;
    }

    public ResponseEntity<?> commentOnIssue() {
        return null;
    }


    @GetMapping
    public ResponseEntity<?> getIssuesRelatedToUser( HttpSession session ) {
        String emailFromSession = SessionUtils.getEmailFromSession( session );
        List<Issue> issuesByReporterOrAssignee = issueService.getIssuesByReporterOrAssignee( userService.findByEmail( emailFromSession ) );
        List<IssueDTO> issueDTOS = IssueDTO.mapIssuesToIssueDTO( issuesByReporterOrAssignee );
        return ResponseEntity.ok( issueDTOS );
    }

    @PostMapping("/{issueId}/assign")
    public ResponseEntity<?> assignIssueToUser( @PathVariable @Valid UUID issueId, @RequestBody AssignIssueDTO data, HttpSession session ) {
        String emailFromSession = SessionUtils.getEmailFromSession( session );
        Optional<Issue> issueById = issueService.findIssueById( issueId );
        issueById.ifPresent( issue -> issueService.assignIssueToUser( issue, userService.findByEmail( data.email() ) ) );
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{issueId}/assign")
    public ResponseEntity<?> removeAssignment( @PathVariable @Valid UUID issueId, HttpSession session ) {
        Optional<Issue> issueById = issueService.findIssueById( issueId );
        issueById.ifPresent( issueService::removeAssignment );
        return ResponseEntity.noContent().build();
    }
}
