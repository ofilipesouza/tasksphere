package dev.ofilipesouza.tasksphere.exception;

public class ProjectNotFoundException extends RuntimeException{
    public ProjectNotFoundException(String message){
        super(message);
    }
}
