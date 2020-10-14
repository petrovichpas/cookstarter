package ru.guteam.restaurant_service.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import ru.guteam.restaurant_service.model.Restaurant;
import org.springframework.stereotype.Service;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    // Мок хранилище ресторанов
    private static final Map<Integer, Restaurant> RESTAURANT_REPOSITORY_MAP = new HashMap<>();

    // Переменная для генерации ID ресторана
    private static final AtomicInteger RESTAURANT_ID_HOLDER = new AtomicInteger();

    @Override
    public void create(Restaurant restaurant) {
        final int restaurantId = RESTAURANT_ID_HOLDER.incrementAndGet();
        restaurant.setId(restaurantId);
        RESTAURANT_REPOSITORY_MAP.put(restaurantId, restaurant);
    }

    @Override
    public List<Restaurant> readAll() {
        return new ArrayList<>(RESTAURANT_REPOSITORY_MAP.values());
    }

    @Override
    public Restaurant read(int id) {
        return RESTAURANT_REPOSITORY_MAP.get(id);
    }

    @Override
    public boolean update(Restaurant restaurant, int id) {
        if (RESTAURANT_REPOSITORY_MAP.containsKey(id)) {
            restaurant.setId(id);
            RESTAURANT_REPOSITORY_MAP.put(id, restaurant);
            return true;
        }

        return false;
    }

    @Override
    public boolean delete(int id) {
        return RESTAURANT_REPOSITORY_MAP.remove(id) != null;
    }
}
