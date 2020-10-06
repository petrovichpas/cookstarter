package ru.guteam.cookstarter.api.dto.customerservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.guteam.cookstarter.api.enums.JwtCheckStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtCheckResponse {
    private JwtCheckStatus status;
}
