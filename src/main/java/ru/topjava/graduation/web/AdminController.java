package ru.topjava.graduation.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.topjava.graduation.model.Restaurant;
import ru.topjava.graduation.service.RestaurantService;

import java.net.URI;
import java.util.List;

import static ru.topjava.graduation.util.ValidationUtil.*;

@RestController
@RequestMapping(AdminController.ADMIN_URL)
public class AdminController {
//TODO ДОДЕЛАТЬ МЕТОДЫ СО СТАТИСТИКОЙ И РЕДАКТИРОВАНИЕМ ЕДЫ

    private static final Logger LOG = LoggerFactory.getLogger(AdminController.class);

    static final String ADMIN_URL = "rest/admin";

    private RestaurantService restaurantService;

    @Autowired
    public void setRestaurantService(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
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
}
