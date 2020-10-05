package ru.guteam.customer_service.controllers;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.guteam.customer_service.entities.User;
import ru.guteam.customer_service.services.UsersService;
import ru.guteam.customer_service.utils.JwtCheckRequest;
import ru.guteam.customer_service.utils.JwtCheckResponse;
import ru.guteam.customer_service.utils.JwtCheckStatus;
import ru.guteam.customer_service.controllers.utils.JwtTokenUtil;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/check")
@AllArgsConstructor
public class JwtCheckController {
    private final JwtTokenUtil jwtTokenUtil;

    private UsersService usersService;


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public JwtCheckResponse jwtCheck(@RequestBody JwtCheckRequest jwt) {
        String username;
        try {
            username = jwtTokenUtil.getUsernameFromToken(jwt.getToken());
        } catch (ExpiredJwtException e) {
            return new JwtCheckResponse(JwtCheckStatus.OUT_OF_DATE);
        }

        if (username != null) {
            Optional<User> user = usersService.findByPhone(username);
            if (!user.isPresent() || !user.get().isEnable()) {
                return new JwtCheckResponse(JwtCheckStatus.MISSED);
            }
        }
        return new JwtCheckResponse(JwtCheckStatus.ACTIVE);
    }

}
