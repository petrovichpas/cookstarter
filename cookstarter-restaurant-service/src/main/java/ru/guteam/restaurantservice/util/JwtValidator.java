package ru.guteam.restaurantservice.util;

import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtValidator {
    @Value("{jwt.secret}")
    private String secret;

    public void checkJwt(String token) {
        Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token.replace("Bearer", ""));
    }
}
