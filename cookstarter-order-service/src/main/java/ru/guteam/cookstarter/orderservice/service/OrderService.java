package ru.guteam.cookstarter.orderservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.guteam.cookstarter.api.dto.orderservice.OrderDto;
import ru.guteam.cookstarter.orderservice.exception.OrderProcessingException;
import ru.guteam.cookstarter.orderservice.model.Order;
import ru.guteam.cookstarter.orderservice.model.OrderItem;
import ru.guteam.cookstarter.orderservice.repository.OrderItemRepository;
import ru.guteam.cookstarter.orderservice.repository.OrderRepository;

import java.util.Map;
import java.util.stream.Collectors;

import static ru.guteam.cookstarter.orderservice.service.util.OrderMapping.toDto;
import static ru.guteam.cookstarter.orderservice.service.util.OrderMapping.toOrder;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    @Transactional
    public Long insert(OrderDto orderRequest) {
        Order order = toOrder(orderRequest);
        filterZeroQuantity(order);
        if (order.getDishes().isEmpty()) {
            throw new OrderProcessingException("Список блюд пуст или количество равно нулю");
        }
        return orderRepository.save(order).getId();
    }

    private void filterZeroQuantity(Order order) {
        order.setDishes(order.getDishes().entrySet().stream()
                .filter(entry -> entry.getValue().getQuantity() > 0)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
    }

    @Transactional
    public void update(OrderDto orderRequest) {
        Long id = orderRequest.getId();
        checkIdIsNull(id);
        Order order = getOrderByIdOrThrowException(id);
        Order orderNew = toOrder(orderRequest);
        order.setDishes(joinDishMaps(order.getDishes(), orderNew.getDishes()));
        deleteZeroQuantityItems(order);
        filterZeroQuantity(order);
        if (order.getDishes().isEmpty()) {
            deleteOrder(id);
        } else {
            order.setOrderSum(orderNew.getOrderSum());
            orderRepository.save(order);
        }
    }

    private void deleteZeroQuantityItems(Order order) {
        order.getDishes().entrySet().stream()
                .filter(entry -> entry.getValue().getId() != null && entry.getValue().getQuantity() == 0)
                .forEach(entry -> orderItemRepository.delete(entry.getValue()));
    }

    private Map<Long, OrderItem> joinDishMaps(Map<Long, OrderItem> mapOld, Map<Long, OrderItem> mapNew) {
        mapOld.forEach((key, value) -> value.setQuantity(0));
        for (Map.Entry<Long, OrderItem> entry : mapNew.entrySet()) {
            Long id = entry.getKey();
            OrderItem dish = entry.getValue();
            mapOld.compute(id, (itemId, item) -> {
                if (item == null) {
                    return dish;
                }
                item.setQuantity(dish.getQuantity());
                return item;
            });
        }
        return mapOld;
    }


    private void checkIdIsNull(Long id) {
        if (id == null) {
            throw new OrderProcessingException("Не указан id");
        }
    }

    public OrderDto getById(Long id) {
        checkIdIsNull(id);
        return toDto(getOrderByIdOrThrowException(id));
    }

    private Order getOrderByIdOrThrowException(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new OrderProcessingException("Не найден заказ id = " + id));
    }

    @Transactional
    public void deleteOrder(Long id) {
        checkIdIsNull(id);
        getOrderByIdOrThrowException(id);
        orderRepository.deleteById(id);
    }

    @Transactional
    public void deleteItem(Long id) {
        checkIdIsNull(id);
        getItemByIdOrThrowException(id);
        orderItemRepository.deleteById(id);
    }

    private OrderItem getItemByIdOrThrowException(Long id) {
        return orderItemRepository.findById(id)
                .orElseThrow(() -> new OrderProcessingException("Не найдено в заказах блюда id = " + id));
    }
}
