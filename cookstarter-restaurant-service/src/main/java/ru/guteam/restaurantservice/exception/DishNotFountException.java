package ru.guteam.restaurantservice.exception;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

@Getter
public class DishNotFountException extends RuntimeException {
    @Value("{exception.dish}")
    private String message;
}
