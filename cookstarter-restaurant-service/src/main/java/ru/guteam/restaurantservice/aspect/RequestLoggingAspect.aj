package ru.guteam.restaurantservice.aspect;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public aspect RequestLoggingAspect {

    before(): execution(* ru.guteam.restaurantservice.controller.* (..)) {
        log.info("Method invoking - " + thisJoinPoint.getSignature().getName());
    }
}
