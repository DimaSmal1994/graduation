package ru.topjava.graduation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.topjava.graduation.model.Dish;

public interface DishRepository extends JpaRepository<Dish, Integer> {

}
