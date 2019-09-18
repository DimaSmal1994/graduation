package ru.topjava.graduation.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.topjava.graduation.model.Restaurant;
import ru.topjava.graduation.service.DishService;
import ru.topjava.graduation.service.RestaurantService;
import ru.topjava.graduation.to.DishTo;

import java.net.URI;
import java.util.List;

import static ru.topjava.graduation.util.ValidationUtil.checkIdConsistent;
import static ru.topjava.graduation.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(AdminController.ADMIN_URL)
public class AdminController {
//TODO ДОДЕЛАТЬ МЕТОДЫ СО СТАТИСТИКОЙ И РЕДАКТИРОВАНИЕМ ЕДЫ

    private static final Logger LOG = LoggerFactory.getLogger(AdminController.class);

    static final String ADMIN_URL = "admin";

    private RestaurantService restaurantService;

    private DishService dishService;

    @Autowired
    public void setRestaurantService(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @Autowired
    public void setDishService(DishService dishService) {
        this.dishService = dishService;
    }

    @PostMapping(value = "/restaurants", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> saveRestaurant(@RequestBody Restaurant restaurant) {
        checkNew(restaurant);

        LOG.info("saveRestaurant {} ", restaurant);
        Restaurant savedRestaurant = restaurantService.save(restaurant);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(ADMIN_URL + "/{id}")
                .buildAndExpand(savedRestaurant.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(savedRestaurant);
    }

    @PutMapping(value = "/restaurants/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> updateRestaurant(@PathVariable("id") int id, @RequestBody Restaurant restaurant) {
        checkIdConsistent(restaurant, id);
        LOG.info("updateRestaurant {} with id={}", restaurant, id);

        Restaurant updatedRestaurant = restaurantService.update(restaurant);

        return ResponseEntity.ok().body(updatedRestaurant);
    }

    @DeleteMapping(value = "/restaurants/{id}")
    public ResponseEntity<Restaurant> deleteRestaurant(@PathVariable int id) {
        LOG.info("delete restaurant with id={}", id);
        restaurantService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/restaurants/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> getRestaurantById(@PathVariable int id) {
        LOG.info("get restaurant with id={}", id);
        Restaurant restaurant = restaurantService.getById(id);

        return ResponseEntity.ok(restaurant);
    }

    @GetMapping(value = "/restaurants", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Restaurant>> getAllRestaurants() {
        LOG.info("get all restaurants");
        List<Restaurant> restaurants = restaurantService.getAll();

        return ResponseEntity.ok(restaurants);
    }

    @PostMapping(value = "/dishes", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DishTo> saveDish(@RequestBody DishTo dishTo) {
        checkNew(dishTo);
        LOG.info("create {}", dishTo);
        DishTo savedDish = dishService.save(dishTo);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(ADMIN_URL + "/{id}")
                .buildAndExpand(savedDish.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(savedDish);
    }

    @DeleteMapping(value = "/dishes/{id}")
    public ResponseEntity<DishTo> deleteDish(@PathVariable("id") int id) {
        LOG.info("delete dish with id={}", id);
        dishService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/restaurants/{id}/dishes", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DishTo>> getAllDishesOfRestaurant(@PathVariable("id") int id) {
        LOG.info("get all dishes of restaurant with id={}", id);
        List<DishTo> dishes = dishService.getAllByRestaurantId(id);

        return ResponseEntity.ok(dishes);
    }

    @PutMapping(value = "/dishes/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DishTo> updateDish(@RequestBody DishTo dishTo, @PathVariable("id") int id) {
        checkIdConsistent(dishTo, id);
        LOG.info("update {} with id={}", dishTo, id);
        DishTo updatedDish = dishService.update(dishTo);

        return ResponseEntity.ok(updatedDish);
    }
}
