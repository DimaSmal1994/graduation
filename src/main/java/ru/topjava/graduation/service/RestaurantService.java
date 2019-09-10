package ru.topjava.graduation.service;

import ru.topjava.graduation.model.Restaurant;
import ru.topjava.graduation.repository.MockRestaurantRepository;

import java.util.List;

public class RestaurantService {
    private MockRestaurantRepository repository;

    public RestaurantService() {
    }

    public RestaurantService(MockRestaurantRepository repository) {
        this.repository = repository;
    }

    public void setRepository(MockRestaurantRepository repository) {
        this.repository = repository;
    }

    public Restaurant create(Restaurant restaurant) {
        Restaurant result;
        if (restaurant != null && restaurant.getName() != null) {
            result = repository.save(restaurant);
        } else {
            result = null;
        }

        return result;
    }

    public void delete(Restaurant restaurant) {
        if (restaurant != null && repository.getByName(restaurant.getName()) != null) {
            repository.delete(restaurant);
        }
    }

    public List<Restaurant> getAll() {
        return repository.getAll();
    }

    public Restaurant getByName(String name) {
        Restaurant result;
        if (name != null) {
            result = repository.getByName(name);
        } else {
            result = null;
        }

        return result;
    }

    public void update(Restaurant restaurant) {
        if (restaurant != null) {
            repository.save(restaurant);
        }
    }
}
