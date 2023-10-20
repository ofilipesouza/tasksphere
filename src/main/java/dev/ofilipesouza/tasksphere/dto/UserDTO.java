package dev.ofilipesouza.tasksphere.dto;

import dev.ofilipesouza.tasksphere.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;


@Getter
@AllArgsConstructor
public class UserDTO {

    private String firstName;
    private String lastName;
    private String email;
    private LocalDateTime createdAt;

    public static UserDTO mapUserToUserDTO(User user){
        return user != null ? new UserDTO(user.getFirstName(), user.getLastName(), user.getEmail(), user.getCreatedDate()) : null;
    }

}
