package ru.guteam.web_application.entities.dto;

import lombok.Data;

@Data
public class JwtRequest {
    private String username;
    private String password;
}
