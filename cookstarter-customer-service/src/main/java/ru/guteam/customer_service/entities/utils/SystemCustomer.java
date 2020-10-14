package ru.guteam.customer_service.entities.utils;


import lombok.Data;
import lombok.NoArgsConstructor;
import ru.guteam.customer_service.entities.utils.validation.FieldMatch;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@FieldMatch(first = "password", second = "matchingPassword", message = "The password fields must match")
public class SystemCustomer {

    @NotNull(message = "This field is required")
    @Size(min = 2, message = "Phone number length must be 10 symbols")
    private String username;

    @NotNull(message = "This field is required")
    @Size(min = 4, message = "Password is too short")
    private String password;

    @NotNull(message = "This field is required")
    @Size(min = 4, message = "Password is too short")
    private String matchingPassword;

    @NotNull(message = "This field is required")
    private String firstName;

    @NotNull(message = "This field is required")
    private String lastName;

    @NotNull(message = "This field is required")
    @Email(message = "This field must be field of email")
    private String email;
}
