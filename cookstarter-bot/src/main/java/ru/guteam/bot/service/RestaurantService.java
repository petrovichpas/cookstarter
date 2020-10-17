package ru.guteam.bot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.guteam.bot.sender.RestaurantSender;

@Service
public class RestaurantService {
    private RestaurantSender restaurantSender;

    @Autowired
    public RestaurantService(RestaurantSender restaurantSender) {
        this.restaurantSender = restaurantSender;
    }
}
