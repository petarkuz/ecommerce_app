package mk.finki.ukim.wp.model.dto;

import lombok.Data;
import mk.finki.ukim.wp.model.User;
import mk.finki.ukim.wp.model.enumerations.Role;

@Data
public class UserDetailsDto {
    private String username;
    private Role role;

    public static UserDetailsDto createOf(User user) {
        UserDetailsDto details = new UserDetailsDto();
        details.username = user.getUsername();
        details.role = user.getRole();
        return details;
    }
}
