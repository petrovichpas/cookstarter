package ru.guteam.customer_service.services.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.guteam.customer_service.controllers.utils.UsernameAndPasswordRequest;

@Slf4j
@Aspect
@Configuration
public class LoggingRequestAspect {

    @Before("execution(* ru.guteam.customer_service.controllers.AuthController.* (..))")
    public void logAuthRequest(JoinPoint joinPoint) {
        UsernameAndPasswordRequest request = (UsernameAndPasswordRequest) joinPoint.getArgs()[0];
        log.info("Запрос на аутентификацию пользователя с логином: " + request.getUsername() +
                " и паролем: " + request.getPassword());
    }

    @AfterReturning(pointcut = "execution(* ru.guteam.customer_service.controllers.AuthController.* (..))", returning = "result")
    public void logCheckResponse(JoinPoint joinPoint, Object result) {
        ResponseEntity response = (ResponseEntity) result;
        if (response.getStatusCode().equals(HttpStatus.UNAUTHORIZED)){
            log.info("Пользователь с логином: " + authRequest.getUsername() +
                    " и паролем: " + authRequest.getPassword() + " не обнаружен");    }
        }
        JwtCheckRequest request = (JwtCheckRequest) joinPoint.getArgs()[0];


}
