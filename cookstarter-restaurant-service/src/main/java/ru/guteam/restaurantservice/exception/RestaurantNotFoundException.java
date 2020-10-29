package ru.guteam.restaurantservice.exception;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

@Getter
public class RestaurantNotFoundException extends RuntimeException {
    @Value("{exception.restaurant}")
    private String message;
}
