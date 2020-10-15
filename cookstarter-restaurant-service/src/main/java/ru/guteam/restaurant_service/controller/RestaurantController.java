package ru.guteam.restaurant_service.controller;

import java.util.List;

import ru.guteam.restaurant_service.model.Restaurant;
import ru.guteam.restaurant_service.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestaurantController {

    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @PostMapping(value = "/restaurants")
    public ResponseEntity<?> create(@RequestBody Restaurant restaurant) {
        restaurantService.create(restaurant);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/restaurants")
    public ResponseEntity<List<Restaurant>> read() {
        final List<Restaurant> restaurants = restaurantService.readAll();

        return restaurants != null && !restaurants.isEmpty()
                ? new ResponseEntity<>(restaurants, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/restaurants/{id}")
    public ResponseEntity<Restaurant> read(@PathVariable(name = "id") int id) {
        final Restaurant restaurant = restaurantService.read(id);

        return restaurant != null
                ? new ResponseEntity<>(restaurant, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/restaurants/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        final boolean deleted = restaurantService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
