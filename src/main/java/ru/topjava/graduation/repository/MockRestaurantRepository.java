package ru.topjava.graduation.repository;

import ru.topjava.graduation.model.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class MockRestaurantRepository {
    private List<Restaurant> list;

    public MockRestaurantRepository() {
        list = new ArrayList<>();
    }

    public MockRestaurantRepository(List<Restaurant> list) {
        this.list = list;
    }

    public void setList(List<Restaurant> list) {
        this.list = list;
    }

    public List<Restaurant> getAll() {
        return list;
    }

    public boolean delete(Restaurant restaurant) {
        return list.remove(restaurant);
    }

    public Restaurant save(Restaurant restaurant) {
        boolean updated = false;
        for (Restaurant currentRestaurant : new ArrayList<>(list)) {
            if (currentRestaurant.getName().equals(restaurant.getName())) {
                list.remove(currentRestaurant);
                list.add(restaurant);
                updated = true;
            }
        }

        if (!updated) {
            list.add(restaurant);
        }

        return restaurant;
    }

    public Restaurant getByName(String name) {
        Restaurant result = null;
        for (Restaurant currentRestaurant : list) {
            if (currentRestaurant.getName().equals(name)) {
                result = currentRestaurant;
            }
        }

        return result;
    }


}
