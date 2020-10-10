package ru.guteam.web_application.exceptions;

public class SearchingNotFoundException extends RuntimeException {
    public SearchingNotFoundException(String message) {
        super(message);
    }
}
