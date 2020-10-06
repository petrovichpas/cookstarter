package ru.guteam.cookstarter.orderservice.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Service
public class AuthServiceStub implements AuthService {

    @Override
    public void checkToken(String token) {
        // заглушка customer service
        // удалить класс после его реализации
    }
}
