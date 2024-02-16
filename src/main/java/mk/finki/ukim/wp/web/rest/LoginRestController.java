package mk.finki.ukim.wp.web.rest;


import com.fasterxml.jackson.core.JsonProcessingException;
import mk.finki.ukim.wp.config.filter.JwtAuthenticationFilter;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
@RequestMapping("/api/login")
public class LoginRestController {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public LoginRestController(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @PostMapping
    public String login(HttpServletRequest request,
                        HttpServletResponse response) throws JsonProcessingException {

        Authentication authentication = this.jwtAuthenticationFilter.attemptAuthentication(request, response);
        return this.jwtAuthenticationFilter.generateJwt(response, authentication);
    }
}
