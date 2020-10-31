package ru.guteam.restaurantservice.service;

import ru.guteam.restaurantservice.model.Dish;

public interface DishService {
    void saveDish(Dish dish);

    void updateDish(Dish dish);

    void deleteDish(Dish dish);
}
