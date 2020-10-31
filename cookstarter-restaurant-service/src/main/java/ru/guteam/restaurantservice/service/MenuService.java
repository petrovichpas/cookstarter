package ru.guteam.restaurantservice.service;

import ru.guteam.restaurantservice.dto.Menu;

public interface MenuService {
    void createMenu(Menu menu);

    Menu getMenu(Long restaurant_id);

}
