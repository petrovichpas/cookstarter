package ru.guteam.cookstarter.api.dto.orderservice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private Long id;
    @NotNull
    private Long customerId;
    @NotNull
    private Long restaurantId;
    @Valid
    @NotNull
    private Map<Long,Item> dishes;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Item {
        private Long id;
        @Positive
        private BigDecimal price;
        @PositiveOrZero
        private Integer quantity;
    }
}
