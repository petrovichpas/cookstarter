package ru.guteam.cookstarter.orderservice.service.util;

import lombok.experimental.UtilityClass;
import ru.guteam.cookstarter.api.dto.orderservice.OrderDto;
import ru.guteam.cookstarter.orderservice.model.Order;
import ru.guteam.cookstarter.orderservice.model.OrderItem;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@UtilityClass
public class OrderMapping {

    public static Order toOrder(OrderDto orderDto) {
        Order order = Order.builder()
                .id(orderDto.getId())
                .customerId(orderDto.getCustomerId())
                .restaurantId(orderDto.getRestaurantId())
                .build();

        Map<Long, OrderItem> dishesList = new HashMap<>();
        BigDecimal orderSum = new BigDecimal(0);
        Map<Long, OrderDto.Item> dishes = orderDto.getDishes();
        for (Map.Entry<Long, OrderDto.Item> entry : dishes.entrySet()) {
            OrderDto.Item dish = entry.getValue();
            BigDecimal price = dish.getPrice();
            Integer quantity = dish.getQuantity();
            dishesList.put(entry.getKey(), OrderItem.builder()
                    .id(dish.getId())
                    .price(price)
                    .quantity(quantity)
                    .order(order)
                    .build());
            orderSum = orderSum.add(price.multiply(BigDecimal.valueOf(quantity)));
        }
        order.setDishes(dishesList);
        order.setOrderSum(orderSum);
        return order;
    }

    public static OrderDto toDto(Order order) {
        Map<Long, OrderItem> dishes = order.getDishes();
        return OrderDto.builder()
                .id(order.getId())
                .customerId(order.getCustomerId())
                .restaurantId(order.getRestaurantId())
                .dishes(dishes.entrySet().stream()
                        .collect(Collectors.toMap(
                                Map.Entry::getKey,
                                entry -> OrderDto.Item.builder()
                                        .id(entry.getValue().getId())
                                        .price(entry.getValue().getPrice())
                                        .quantity(entry.getValue().getQuantity())
                                        .build())))
                .build();
    }
}
