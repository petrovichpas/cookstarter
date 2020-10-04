package ru.guteam.customer_service.controllers;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.guteam.customer_service.entities.User;
import ru.guteam.customer_service.services.UsersService;
import ru.guteam.customer_service.utils.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/token")
@AllArgsConstructor
public class ValidationController {
    private final JwtTokenUtil jwtTokenUtil;

    private UsersService usersService;


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public JwtValidationResponse processRegistrationForm(@RequestBody JwtWrapper jwt) {
        String username;
        System.out.println("ghgfhf");
        try {
            username = jwtTokenUtil.getUsernameFromToken(jwt.getToken());
        } catch (ExpiredJwtException e) {
            return new JwtValidationResponse(Status.OUT_OF_DATE.getValue());
        }

        if (username != null) {
            Optional<User> user = usersService.findByPhone(username);
            if (!user.isPresent()) {
                return new JwtValidationResponse(Status.MISSED.getValue());
            }
        }
        return new JwtValidationResponse(Status.ACTIVE.getValue());
    }

}
