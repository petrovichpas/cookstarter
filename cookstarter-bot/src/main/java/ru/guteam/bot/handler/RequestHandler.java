package ru.guteam.bot.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.guteam.bot.service.CustomerService;
import ru.guteam.bot.service.OrderService;
import ru.guteam.bot.service.PictureService;
import ru.guteam.bot.service.RestaurantService;

@Component
public class RequestHandler {
    private RestaurantService restaurantService;
    private CustomerService customerService;
    private OrderService orderService;
    private PictureService pictureService;

    @Autowired
    public RequestHandler(RestaurantService restaurantService, CustomerService customerService,
                          OrderService orderService, PictureService pictureService) {
        this.restaurantService = restaurantService;
        this.customerService = customerService;
        this.orderService = orderService;
        this.pictureService = pictureService;
    }
}
