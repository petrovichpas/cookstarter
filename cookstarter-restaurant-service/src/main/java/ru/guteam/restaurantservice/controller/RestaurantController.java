package ru.guteam.restaurantservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.guteam.restaurantservice.model.Restaurant;
import ru.guteam.restaurantservice.service.RestaurantService;

import java.util.List;

import static ru.guteam.restaurantservice.util.RequestHeaders.JWT_HEADER;

@RestController
@RequestMapping("/restaurant")
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantService restaurantService;

    @CrossOrigin
    @PostMapping("/add")
    public ResponseEntity<Long> addRestaurant(@RequestHeader(JWT_HEADER) String token,
                                              @RequestBody Restaurant restaurant) {
        Long restaurant_id = restaurantService.saveRestaurant(restaurant);
        return new ResponseEntity(restaurant_id, HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/getByName/{name}")
    public ResponseEntity<List> getRestaurantsByName(@RequestHeader(JWT_HEADER) String token,
                                                     @PathVariable String name) {
        List<Restaurant> restaurantsByName = restaurantService.getRestaurantsByName(name);
        return new ResponseEntity(restaurantsByName, HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/getByAddress/{address}")
    public ResponseEntity<List> getRestaurantsByAddress(@RequestHeader(JWT_HEADER) String token,
                                                        @PathVariable String address) {
        List<Restaurant> restaurantsByAddress = restaurantService.getRestaurantsByAddress(address);
        return new ResponseEntity(restaurantsByAddress, HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping("/update")
    public ResponseEntity updateRestaurant(@RequestHeader(JWT_HEADER) String token,
                                           @RequestBody Restaurant restaurant) {
        restaurantService.updateRestaurant(restaurant);
        return new ResponseEntity(HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/delete/{id}")
    public ResponseEntity deleteRestaurant(@RequestHeader(JWT_HEADER) String token,
                                           @PathVariable Long id) {
        restaurantService.deleteRestaurant(id);
        return new ResponseEntity(HttpStatus.OK);
    }


}
