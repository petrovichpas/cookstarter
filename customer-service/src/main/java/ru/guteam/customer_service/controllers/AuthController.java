package ru.guteam.customer_service.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.guteam.customer_service.services.UsersService;
import ru.guteam.customer_service.utils.UsernameAndPasswordWrapper;
import ru.guteam.customer_service.utils.JwtWrapper;
import ru.guteam.customer_service.utils.JwtTokenUtil;

import java.util.List;


@RestController
@CrossOrigin("*") // добавить сервисы
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
    private final UsersService usersService;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public JwtWrapper createAuthToken(@RequestBody UsernameAndPasswordWrapper authRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException e) {
            System.out.println(e.getMessage());        }
        UserDetails userDetails = usersService.loadUserByUsername(authRequest.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails);
        return new JwtWrapper(token);
    }

}
