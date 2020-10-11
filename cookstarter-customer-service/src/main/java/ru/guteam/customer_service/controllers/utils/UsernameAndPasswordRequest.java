package ru.guteam.customer_service.controllers.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsernameAndPasswordRequest {
    @NotNull
    private String username;
    @NotNull
    private String password;
}
