package ru.guteam.bot;

import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;
import ru.guteam.bot.rest.RestClient;

public class RestClientTest {
    @Test
    public void restClientTest() {

        RestTemplate restTemplate = new RestTemplate();
        RestClient restClient = new RestClient(restTemplate);

//        String token = restClient.getToken("lol", "1234");

//        System.out.println(token);
    }

}
