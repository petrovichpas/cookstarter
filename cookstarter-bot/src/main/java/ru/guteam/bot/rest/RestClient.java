package ru.guteam.bot.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class RestClient {
    private final RestTemplate restTemplate;

    @Autowired
    public RestClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getToken(String username, String password, String url) {
        AuthRequest authRequest = new AuthRequest(username, password);

        return restTemplate.postForObject(url, authRequest, String.class);
    }
}
