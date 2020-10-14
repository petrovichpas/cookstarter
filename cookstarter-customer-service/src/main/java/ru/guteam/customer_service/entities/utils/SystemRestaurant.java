package ru.guteam.customer_service.entities.utils;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.guteam.customer_service.entities.Customer;
import ru.guteam.customer_service.entities.Role;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
public class SystemRestaurant {

    @NotNull
    private Long restaurantId;
    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private String role;

}
