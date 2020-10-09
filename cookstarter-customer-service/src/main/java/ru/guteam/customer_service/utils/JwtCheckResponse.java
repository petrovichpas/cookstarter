package ru.guteam.customer_service.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.guteam.customer_service.utils.JwtCheckStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtCheckResponse {
    private JwtCheckStatus status;
}
