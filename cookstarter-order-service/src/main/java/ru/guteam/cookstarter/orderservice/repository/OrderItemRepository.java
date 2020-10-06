package ru.guteam.cookstarter.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.guteam.cookstarter.orderservice.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
