package ru.guteam.restaurantservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.guteam.restaurantservice.exception.RestaurantNotFoundException;
import ru.guteam.restaurantservice.model.Restaurant;
import ru.guteam.restaurantservice.repo.RestaurantRepo;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepo restaurantRepo;

    @Override
    public Restaurant getRestaurant(Long id) {
        return restaurantRepo.findById(id).orElseThrow(RestaurantNotFoundException::new);
    }

    @Override
    @Transactional
    public Long saveRestaurant(Restaurant restaurant) {
        restaurantRepo.save(restaurant);
        Long restaurant_id = restaurantRepo.findByName(restaurant.getName())
                .orElseThrow(RestaurantNotFoundException::new).getId();
        return restaurant_id;
    }

    @Override
    @Transactional
    public List<Restaurant> getRestaurantsByName(String name) {
        List<Restaurant> restaurants = restaurantRepo.findByNameLike(name)
                .orElseThrow(RestaurantNotFoundException::new);
        return restaurants;
    }

    @Override
    @Transactional
    public List<Restaurant> getRestaurantsByAddress(String address) {
        List<Restaurant> restaurants = restaurantRepo.findByAddress(address)
                .orElseThrow(RestaurantNotFoundException::new);
        return restaurants;
    }

    @Override
    @Transactional
    public void updateRestaurant(Restaurant restaurant) {
        saveRestaurant(restaurant);
    }

    @Override
    @Transactional
    public void deleteRestaurant(Long id) {
        restaurantRepo.deleteById(id);
    }
}
