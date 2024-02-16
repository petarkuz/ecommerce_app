package mk.finki.ukim.wp.service;

import mk.finki.ukim.wp.model.User;
import mk.finki.ukim.wp.model.enumerations.Role;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User register(String username, String password, String repeatPassword, String name, String surname, Role role);

}
