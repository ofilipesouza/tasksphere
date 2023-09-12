package dev.ofilipesouza.tasksphere.controller;

import dev.ofilipesouza.tasksphere.dto.LoginDTO;
import dev.ofilipesouza.tasksphere.dto.RegisterDTO;
import dev.ofilipesouza.tasksphere.model.User;
import dev.ofilipesouza.tasksphere.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
    private final UserRepository repository;
    private final AuthenticationManager authenticationManager;

    public AuthenticationController(UserRepository repository, AuthenticationManager authenticationManager) {
        this.repository = repository;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid RegisterDTO data ){

        String encryptedPass = new BCryptPasswordEncoder().encode(data.password());

        User user = new User();
        user.setFirstName(data.firstName());
        user.setLastName(data.lastName());
        user.setEmail(data.email());
        user.setCreatedDate(LocalDateTime.now());
        user.setPassword(encryptedPass);

        repository.save(user);

        return ResponseEntity.ok("User registered! 👨🏾‍💻 ");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginDTO data, HttpSession httpSession){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        this.authenticationManager.authenticate(usernamePassword);

        httpSession.setAttribute("username",data.email());
        return ResponseEntity.ok("Logged in! ⚡️");
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response){
        new SecurityContextLogoutHandler().logout(request, response, null);
        return ResponseEntity.ok("Logged out! 😴");
    }

}
