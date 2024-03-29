package dev.ofilipesouza.tasksphere.infra.security;

import dev.ofilipesouza.tasksphere.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Order(Ordered.LOWEST_PRECEDENCE)
@Slf4j
public class SessionValidationFilter extends OncePerRequestFilter {
    private final UserRepository userRepository;

    public SessionValidationFilter( UserRepository userRepository ) {
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal( HttpServletRequest request, HttpServletResponse response, FilterChain filterChain )
            throws ServletException, IOException {

        SecurityContextHolder.getContext().getAuthentication();

        if (request.isRequestedSessionIdValid()) {
            String email = (String) request.getSession().getAttribute( "username" );
            if (email != null) {
                UserDetails user = userRepository.findByEmail( email );
                var authentication = new UsernamePasswordAuthenticationToken( user, null, user.getAuthorities() );
                SecurityContextHolder.getContext().setAuthentication( authentication );
            }
        }
        filterChain.doFilter( request, response );
    }
}
