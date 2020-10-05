package ru.guteam.customer_service.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsernameAndPassword {
    private String username;
    private String password;
}
