package ru.guteam.restaurant_service.service;

import java.util.List;

import ru.guteam.restaurant_service.model.Restaurant;

/**
 * Интерфейс реализующий CRUD операции.
 */
public interface RestaurantService {

    void create(Restaurant restaurant);

    List<Restaurant> readAll();

    Restaurant read(int id);

    boolean update(Restaurant restaurant, int id);

    boolean delete(int id);
}
