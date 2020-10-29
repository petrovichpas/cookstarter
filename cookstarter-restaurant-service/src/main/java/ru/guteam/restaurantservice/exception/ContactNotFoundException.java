package ru.guteam.restaurantservice.exception;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

@Getter
public class ContactNotFoundException extends RuntimeException {
    @Value("{exception.contact}")
    private String message;
}
