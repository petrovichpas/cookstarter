package ru.guteam.cookstarter.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.guteam.cookstarter.orderservice.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
