package ru.topjava.graduation.repository;

import ru.topjava.graduation.model.RestaurantRating;

import java.util.ArrayList;
import java.util.List;

public class MockRestaurantRatingRepository {
    private List<RestaurantRating> ratings;

    public MockRestaurantRatingRepository() {
        ratings = new ArrayList<>();
    }

    public List<RestaurantRating> getAll() {
        return ratings;
    }

    public RestaurantRating save(RestaurantRating rating) {
        boolean updated = false;
        for (RestaurantRating currentRating : new ArrayList<>(ratings)) {
            if (currentRating.getRestaurantName().equals(rating.getRestaurantName())) {
                ratings.remove(currentRating);
                ratings.add(rating);
                updated = true;
            }
        }

        if (!updated) {
            ratings.add(rating);
        }

        return rating;
    }

    public RestaurantRating getByName(String name) {
        RestaurantRating result = null;
        for (RestaurantRating currentRating : ratings) {
            if (currentRating.getRestaurantName().equals(name)) {
                result = currentRating;
            }
        }

        return result;
    }

    public boolean delete(RestaurantRating rating) {
        boolean result;
        if (ratings.contains(rating)) {
            ratings.remove(rating);
            result = true;
        } else {
            result = false;
        }

        return result;
    }

}
