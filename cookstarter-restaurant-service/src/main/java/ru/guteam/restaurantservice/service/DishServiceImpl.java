package ru.guteam.restaurantservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.guteam.restaurantservice.exception.DishNotFountException;
import ru.guteam.restaurantservice.model.Dish;
import ru.guteam.restaurantservice.repo.DishRepo;

@Service
@RequiredArgsConstructor
public class DishServiceImpl implements DishService {
    private final DishRepo dishRepo;


    @Override
    @Transactional
    public void saveDish(Dish dish) {
        dishRepo.save(dish);
    }

    @Override
    @Transactional
    public void updateDish(Dish dish) {
        Dish oldDish = dishRepo.findByNameAndRestaurantId(dish.getName(), dish.getRestaurantId())
                .orElseThrow(DishNotFountException::new);
        dish.setId(oldDish.getId());
        saveDish(dish);
    }

    @Override
    @Transactional
    public void deleteDish(Dish dish) {
        dishRepo.deleteByNameAndRestaurantId(dish.getName(), dish.getRestaurantId());
    }
}
