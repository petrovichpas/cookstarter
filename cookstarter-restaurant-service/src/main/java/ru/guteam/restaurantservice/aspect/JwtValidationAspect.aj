package ru.guteam.restaurantservice.aspect;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.guteam.restaurantservice.util.JwtValidator;

@Slf4j
@Component
public aspect JwtValidationAspect {
    @Autowired
    private JwtValidator jwtValidator;


    before(): execution(* ru.guteam.restaurantservice.controller.* (..)) {
        String token = (String) thisJoinPoint.getArgs()[0];
        log.info("Checking token.");
        jwtValidator.checkJwt(token);
    }
}
