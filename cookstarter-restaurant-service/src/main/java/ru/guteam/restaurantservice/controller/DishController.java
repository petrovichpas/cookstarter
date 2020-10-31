package ru.guteam.restaurantservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.guteam.restaurantservice.model.Dish;
import ru.guteam.restaurantservice.service.DishService;

import static ru.guteam.restaurantservice.util.RequestHeaders.JWT_HEADER;

@RestController
@RequestMapping("/dish")
@RequiredArgsConstructor
public class DishController {
    private final DishService dishService;

    @CrossOrigin
    @PostMapping("/add")
    public ResponseEntity addDish(@RequestHeader(JWT_HEADER) String token,
                                  @RequestBody Dish dish) {
        dishService.saveDish(dish);
        return new ResponseEntity(HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping("/update")
    public ResponseEntity updateDish(@RequestHeader(JWT_HEADER) String token,
                                     @RequestBody Dish dish) {
        dishService.updateDish(dish);
        return new ResponseEntity(HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/delete")
    public ResponseEntity deleteDish(@RequestHeader(JWT_HEADER) String token,
                                     @RequestBody Dish dish) {
        dishService.deleteDish(dish);
        return new ResponseEntity(HttpStatus.OK);
    }

}
