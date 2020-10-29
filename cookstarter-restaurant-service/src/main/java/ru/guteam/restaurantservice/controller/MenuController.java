package ru.guteam.restaurantservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.guteam.restaurantservice.dto.Menu;
import ru.guteam.restaurantservice.service.MenuService;

import static ru.guteam.restaurantservice.util.RequestHeaders.JWT_HEADER;

@RestController
@RequestMapping("/menu")
@RequiredArgsConstructor
public class MenuController {
    private final MenuService menuService;

    @CrossOrigin
    @PostMapping("/add")
    public ResponseEntity addMenu(@RequestHeader(JWT_HEADER) String token,
                                  @RequestBody Menu menu) {
        menuService.createMenu(menu);
        return new ResponseEntity(HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/get/{restaurant_id}")
    public ResponseEntity<Menu> getMenu(@RequestHeader(JWT_HEADER) String token,
                                        @PathVariable Long restaurant_id) {
        Menu menu = menuService.getMenu(restaurant_id);
        return new ResponseEntity(menu, HttpStatus.OK);
    }


}
