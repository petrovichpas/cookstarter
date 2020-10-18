package ru.guteam.picture_service.exception;

public class TokenNotActiveException extends RuntimeException {
    public TokenNotActiveException(String message) {
        super(message);
    }
}
