package dev.ofilipesouza.tasksphere.service.impl;

import org.springframework.stereotype.Service;
import dev.ofilipesouza.tasksphere.controller.UserService;
import dev.ofilipesouza.tasksphere.model.User;
import dev.ofilipesouza.tasksphere.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findByEmail(String email) {
        User user = (User) userRepository.findByEmail(email);
        return user;
    }
    
}
