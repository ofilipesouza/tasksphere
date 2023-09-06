package dev.ofilipesouza.tasksphere.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @PostMapping("/register")
    public ResponseEntity<String> register(){
        return ResponseEntity.ok(new String("Registrado"));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(){
        return ResponseEntity.ok(new String("Login"));
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(){
        return ResponseEntity.ok(new String("Logout"));
    }
    
}
