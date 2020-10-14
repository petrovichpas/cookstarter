package ru.guteam.customer_service.controllers.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtCheckResponse {

    private JwtCheckStatus status;
}
