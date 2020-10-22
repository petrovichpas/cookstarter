package ru.guteam.customer_service.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.guteam.customer_service.entities.utils.SystemCustomer;
import ru.guteam.customer_service.entities.utils.SystemRestaurant;
import ru.guteam.customer_service.entities.utils.validation.rest.ValidationErrorDTO;
import ru.guteam.customer_service.services.CustomersService;
import ru.guteam.customer_service.services.UsersService;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@CrossOrigin("*")
@RestController
@RequestMapping("/reg")
@AllArgsConstructor
public class RegistrationController {
    private final UsersService usersService;
    private final CustomersService customersService;


    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @PostMapping(value = "/restaurant", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> restaurantRegistration(@RequestBody SystemRestaurant systemRestaurant) {
        // обсудить, по идее эта проверка дб отдельным запросом
        String username = systemRestaurant.getUsername();
        if (usersService.existsByUsername(username)) {
            log.info("Невозможно зарегистрировать. Ресторан с логином: " + username + " уже сущесвует");
            return new ResponseEntity<>("User with username: [" + username + "] is already exist", HttpStatus.CONFLICT);
        }
        usersService.saveRestaurant(systemRestaurant);
        logSuccess(username, systemRestaurant.getPassword());
        return new ResponseEntity<>(systemRestaurant, HttpStatus.OK);
    }

    @PostMapping(value = "/customer", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> customerRegistration(@RequestBody @Valid SystemCustomer systemCustomer) {
        String username = systemCustomer.getUsername();
        if (usersService.existsByUsername(username)) {
            log.info("Невозможно зарегистрировать. Клиент с логином: " + username + " уже сущесвует");
            return new ResponseEntity<>("User with phone number: [" + username + "] is already exist", HttpStatus.CONFLICT);
        }
        customersService.saveBySystemCustomer(systemCustomer);
        logSuccess(username, systemCustomer.getPass1());
        return new ResponseEntity<>(systemCustomer, HttpStatus.OK);
    }

    private void logSuccess(String username, String pass) {
        log.info("Пользователь с логином: " + username +
                " и паролем: " + pass + " зарегистрирован");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.OK)
    public ValidationErrorDTO processValidationError(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        return processFieldErrors(fieldErrors);
    }

    private ValidationErrorDTO processFieldErrors(List<FieldError> fieldErrors) {
        ValidationErrorDTO dto = new ValidationErrorDTO();
        for (FieldError fieldError : fieldErrors) {
            dto.addFieldError(fieldError.getField(), fieldError.getDefaultMessage());
            log.info("Ошибка при заполнении поля " + fieldError.getField() +
                    ": " + fieldError.getDefaultMessage());
        }
        return dto;
    }

}
