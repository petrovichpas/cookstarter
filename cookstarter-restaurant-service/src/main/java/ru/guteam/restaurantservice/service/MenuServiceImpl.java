package ru.guteam.restaurantservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.guteam.restaurantservice.dto.Menu;
import ru.guteam.restaurantservice.exception.GetMenuException;
import ru.guteam.restaurantservice.model.Dish;
import ru.guteam.restaurantservice.repo.DishRepo;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {
    private final DishRepo dishRepo;

    @Override
    @Transactional
    public void createMenu(Menu menu) {
        dishRepo.saveAll(menu.getDishes());
    }

    @Override
    @Transactional
    public Menu getMenu(Long restaurant_id) {
        List<Dish> dishes = dishRepo.findAllByRestaurantId(restaurant_id).orElseThrow(GetMenuException::new);
        return new Menu(dishes);
    }
}
