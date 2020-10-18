package ru.guteam.customer_service.services.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;
import ru.guteam.customer_service.controllers.utils.JwtCheckRequest;
import ru.guteam.customer_service.controllers.utils.JwtCheckResponse;
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

    @Before("execution(* ru.guteam.customer_service.controllers.JwtCheckController.* (..))")
    public void logCheckRequest(JoinPoint joinPoint) {
        JwtCheckRequest request = (JwtCheckRequest) joinPoint.getArgs()[0];
        log.info("Запрос на проверку токена: " + request.getToken());
    }

    @AfterReturning(pointcut = "execution(* ru.guteam.customer_service.controllers.JwtCheckController.* (..))", returning = "result")
    public void logCheckResponse(JoinPoint joinPoint, Object result) {
        JwtCheckResponse response = (JwtCheckResponse) result;
        JwtCheckRequest request = (JwtCheckRequest) joinPoint.getArgs()[0];
        log.info("Для токена: " + request.getToken() + " возвращен статус: " + response.getStatus());
    }

}
