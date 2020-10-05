package ru.guteam.customer_service.exceptions;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler
    public ResponseEntity<?> exception(Exception e) {
        log.error("Неизвестная ошибка", e);
        return new ResponseEntity<>("Неизвестная ошибка", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
