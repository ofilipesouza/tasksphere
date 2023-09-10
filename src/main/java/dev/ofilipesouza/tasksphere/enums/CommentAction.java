package dev.ofilipesouza.tasksphere.enums;

public enum CommentAction {
    ADD("add"),
    DELETE("delete"),
    EDIT("edit");

    private String action;

    CommentAction(String action){
        this.action = action;
    }

    public String getAction(){
        return this.action;
    }



}
