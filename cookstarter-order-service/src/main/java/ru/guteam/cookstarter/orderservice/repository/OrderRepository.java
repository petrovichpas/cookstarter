package ru.guteam.cookstarter.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.guteam.cookstarter.orderservice.model.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByCustomerId(Long id);

    List<Order> findAllByRestaurantId(Long id);
}
