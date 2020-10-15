package ru.guteam.customer_service.exceptions;

import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<?> JWTSignatureException(Exception e) {
        log.error("Подлинность токена не установлена", e);
        return new ResponseEntity<>("Подлинность токена не установлена", HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler
    public ResponseEntity<?> exception(Exception e) {
        log.error("Неизвестная ошибка", e);
        return new ResponseEntity<>("Неизвестная ошибка", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
