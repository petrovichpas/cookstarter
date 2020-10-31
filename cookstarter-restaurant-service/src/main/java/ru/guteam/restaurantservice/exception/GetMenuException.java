package ru.guteam.restaurantservice.exception;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

@Getter
public class GetMenuException extends RuntimeException {
    @Value("{exception.menu}")
    private String message;
}
