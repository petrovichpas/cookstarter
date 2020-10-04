package ru.guteam.cookstarter.api.dto.customerservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtCheckRequest {
    @NotNull
    private String token;
}
