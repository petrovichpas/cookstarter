package ru.guteam.cookstarter.orderservice.exception;

public class TokenNotActiveException extends RuntimeException {
    public TokenNotActiveException(String message) {
        super(message);
    }
}
