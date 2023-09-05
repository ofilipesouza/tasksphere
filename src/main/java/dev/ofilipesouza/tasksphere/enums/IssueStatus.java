package dev.ofilipesouza.tasksphere.enums;

public enum IssueStatus {
    OPEN("Open"),
    IN_PROGRESS("In Progress"),
    RESOLVED("Resolved"),
    CLOSED("Closed"),
    REOPENED("Reopened"),
    ON_HOLD("On Hold"),
    BLOCKED("Blocked"),
    IN_REVIEW("In Review"),
    PENDING("Pending"),
    DEFERRED("Deferred");

    private final String displayName;

    IssueStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
