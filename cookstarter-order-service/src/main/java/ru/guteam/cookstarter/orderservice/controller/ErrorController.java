package ru.guteam.cookstarter.orderservice.controller;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.guteam.cookstarter.api.dto.StatusResponse;
import ru.guteam.cookstarter.orderservice.exception.OrderProcessingException;
import ru.guteam.cookstarter.orderservice.exception.TokenNotActiveException;

import java.net.ConnectException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static ru.guteam.cookstarter.orderservice.controller.util.StatusResponseBuilder.error;

@Slf4j
@ControllerAdvice
public class ErrorController {

    @ExceptionHandler({OrderProcessingException.class, TokenNotActiveException.class})
    public ResponseEntity<StatusResponse> badRequest(RuntimeException e) {
        log.error(String.format("Ошибка '%s'", e.getMessage()));
        return error(BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler({InvalidFormatException.class, MethodArgumentNotValidException.class, MismatchedInputException.class})
    public ResponseEntity<StatusResponse> invalidFormat(Exception e) {
        log.error("Ошибка в запросе", e);
        return error(BAD_REQUEST, "Ошибка в запросе");
    }

    @ExceptionHandler(ConnectException.class)
    public ResponseEntity<StatusResponse> connectionLost(InvalidFormatException e) {
        log.error("Нет ответа от сервиса", e);
        return error(INTERNAL_SERVER_ERROR, "Нет соединения");
    }

    @ExceptionHandler
    public ResponseEntity<StatusResponse> exception(Exception e) {
        log.error("Неизвестная ошибка", e);
        return error(INTERNAL_SERVER_ERROR);
    }
}
