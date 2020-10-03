package ru.guteam.cookstarter.orderservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.guteam.cookstarter.api.dto.customerservice.JwtCheckRequest;
import ru.guteam.cookstarter.api.dto.customerservice.JwtCheckResponse;
import ru.guteam.cookstarter.api.enums.JwtCheckStatus;
import ru.guteam.cookstarter.orderservice.exception.TokenNotActiveException;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final RestTemplate restTemplate;
    @Value("${app.customer-service.host}${app.customer-service.path.jwt-check}")
    private String jwtCheckPath;

    public void checkToken(String token) {
        JwtCheckResponse response = restTemplate.postForObject(jwtCheckPath, new JwtCheckRequest(token), JwtCheckResponse.class);
        if (response == null || response.getStatus() != JwtCheckStatus.ACTIVE) {
            throw new TokenNotActiveException("Токен не действителен");
        }
    }
}
