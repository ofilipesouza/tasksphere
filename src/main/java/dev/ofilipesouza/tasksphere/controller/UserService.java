package dev.ofilipesouza.tasksphere.controller;

import dev.ofilipesouza.tasksphere.model.User;

public interface UserService {
    User findByEmail(String email);
}
