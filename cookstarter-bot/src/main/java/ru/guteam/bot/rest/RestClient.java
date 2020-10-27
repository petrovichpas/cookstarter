package ru.guteam.bot.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@Component
public class RestClient {
    private final RestTemplate restTemplate;

    @Autowired
    public RestClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    private String getToken(String username, String password) {
        AuthRequest authRequest = new AuthRequest(username, password);

        return restTemplate.postForObject("http://cookstarter-customer-service.herokuapp.com/auth",
                                              authRequest, String.class);

    }
}
