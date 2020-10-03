package ru.guteam.cookstarter.orderservice.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Aspect
@Configuration
public class LoggingRequestAspect {

    @Before("execution(* ru.guteam.cookstarter.orderservice.controller.OrderController.* (..))")
    public void logRequest(JoinPoint joinPoint) {
        log.info("Вызов метода: " + joinPoint.getSignature().getName());
    }
}
