package mk.finki.ukim.wp.web.rest;

import mk.finki.ukim.wp.model.User;
import mk.finki.ukim.wp.model.dto.UserDetailsDto;
import mk.finki.ukim.wp.model.dto.UserRegistrationDto;
import mk.finki.ukim.wp.model.enumerations.Role;
import mk.finki.ukim.wp.model.exceptions.InvalidUsernameOrPasswordException;
import mk.finki.ukim.wp.model.exceptions.PasswordsDoNotMatchException;
import mk.finki.ukim.wp.model.exceptions.UsernameAlreadyExistsException;
import mk.finki.ukim.wp.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
@RequestMapping("/api/register")
public class RegisterController {

    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity register(@RequestBody UserRegistrationDto registrationDto) {
        try {
            User user = this.userService.register(registrationDto.getUsername(),
                    registrationDto.getPassword(),
                    registrationDto.getRepeatedPassword(),
                    registrationDto.getName(),
                    registrationDto.getSurname(),
                    Role.ROLE_USER);

            return ResponseEntity.status(HttpStatus.CREATED).body(UserDetailsDto.createOf(user));
        } catch (InvalidUsernameOrPasswordException | PasswordsDoNotMatchException | UsernameAlreadyExistsException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }
}
