package ru.topjava.graduation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.topjava.graduation.model.Dish;

import java.util.List;

public interface DishRepository extends JpaRepository<Dish, Integer> {

    List<Dish> getAllByRestaurantId(int id);
}
