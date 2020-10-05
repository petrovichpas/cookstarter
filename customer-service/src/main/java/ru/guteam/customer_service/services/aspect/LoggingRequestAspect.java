package ru.guteam.customer_service.services.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Aspect
@Configuration
public class LoggingRequestAspect {

    @Before("execution(* ru.guteam.customer_service.controllers.AuthController.* (..))")
    public void logAuthRequest(JoinPoint joinPoint) {
        System.out.println("Вызов метода: " + joinPoint.getSignature().getName());
        log.info("Вызов метода: " + joinPoint.getSignature().getName());
    }

    @Before("execution(* ru.guteam.customer_service.controllers.JwtCheckController.* (..))")
    public void logCheckRequest(JoinPoint joinPoint) {
        System.out.println("Вызов метода: " + joinPoint.getSignature().getName());
        log.info("Вызов метода: " + joinPoint.getSignature().getName());
    }

}
