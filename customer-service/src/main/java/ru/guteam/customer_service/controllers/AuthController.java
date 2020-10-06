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
import ru.guteam.customer_service.services.CustomersService;
import ru.guteam.customer_service.controllers.utils.UsernameAndPassword;
import ru.guteam.customer_service.controllers.utils.JwtTokenUtil;


@RestController
@CrossOrigin("*") // добавить сервисы
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
    private final CustomersService customersService;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
            public ResponseEntity<?> createAuthToken(@RequestBody UsernameAndPassword authRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>("Неверные логин или пароль", HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = customersService.loadUserByUsername(authRequest.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

}
