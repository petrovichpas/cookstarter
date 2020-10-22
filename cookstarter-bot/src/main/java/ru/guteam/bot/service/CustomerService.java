package ru.guteam.bot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.guteam.bot.sender.CustomerSender;

@Service
public class CustomerService {
    private CustomerSender customerSender;

    @Autowired
    public CustomerService(CustomerSender customerSender) {
        this.customerSender = customerSender;
    }
}
