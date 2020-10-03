package ru.guteam.cookstarter.api.dto.customerservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtCheckRequest {
    private String token;
}
