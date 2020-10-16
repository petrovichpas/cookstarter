package ru.guteam.bot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.guteam.bot.sender.OrderSender;

@Service
public class OrderService {
    private OrderSender orderSender;

    @Autowired
    public OrderService(OrderSender orderSender) {
        this.orderSender = orderSender;
    }
}
