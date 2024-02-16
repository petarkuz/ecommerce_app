package mk.finki.ukim.wp.model.dto;

import lombok.Data;

@Data
public class UserRegistrationDto {
    private String username;
    private String password;
    private String repeatedPassword;
    private String name;
    private String surname;

    public UserRegistrationDto(String username, String password, String repeatedPassword, String name, String surname) {
        this.username = username;
        this.password = password;
        this.repeatedPassword = repeatedPassword;
        this.name = name;
        this.surname = surname;
    }
}
