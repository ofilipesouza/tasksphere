package dev.ofilipesouza.tasksphere.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import dev.ofilipesouza.tasksphere.dto.LoginDTO;
import dev.ofilipesouza.tasksphere.dto.RegisterDTO;
import dev.ofilipesouza.tasksphere.model.User;
import dev.ofilipesouza.tasksphere.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("api/v1/")
public class AuthenticationController {
    private final UserRepository repository;
    private final AuthenticationManager authenticationManager;
    Logger logger = LoggerFactory.getLogger( AuthenticationController.class );

    public AuthenticationController( UserRepository repository, AuthenticationManager authenticationManager ) {
        this.repository = repository;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    ModelAndView login( @RequestBody @Valid LoginDTO data, HttpSession httpSession, HttpServletResponse response ) {
        var usernamePassword = new UsernamePasswordAuthenticationToken( data.email(), data.password() );
				HashMap<String, String> map = new HashMap<>();
				try {
					authenticationManager.authenticate( usernamePassword );
				} catch (AuthenticationException ae) {
					map.put( "error", "Failed to login!" );
					return new ModelAndView( "error", map );
				}

				httpSession.setAttribute( "username", data.email() );
				response.addHeader( "HX-Redirect", "../dashboard" );
				return new ModelAndView( "dashboard", map );
		}

		@PostMapping("/do-register")
		public ResponseEntity<String> register( @RequestBody @Valid RegisterDTO data ) {

			String encryptedPass = new BCryptPasswordEncoder().encode( data.password() );

			User user = new User();
			user.setFirstName( data.firstName() );
			user.setLastName( data.lastName() );
			user.setEmail( data.email() );
			user.setCreatedDate( LocalDateTime.now() );
			user.setPassword( encryptedPass );

			repository.save( user );

			return ResponseEntity.ok( "User registered! 👨🏾‍💻 " );
		}

		@PostMapping("/logout")
		public ResponseEntity<String> logout( HttpServletRequest request, HttpServletResponse response ) {
			new SecurityContextLogoutHandler().logout( request, response, null );
			return ResponseEntity.ok( "Logged out! 😴" );
		}

}
