package ru.topjava.graduation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.topjava.graduation.model.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

    Restaurant findByNameIsLike(String name);

}
