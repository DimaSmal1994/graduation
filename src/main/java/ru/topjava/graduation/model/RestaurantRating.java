package ru.topjava.graduation.model;

import java.util.Map;

public class RestaurantRating {
    private static Map<String, Integer> ratings;

    public static Map<String, Integer> getRatings() {
        return ratings;
    }

    public static void addVote(String restaurantName) {
        if (ratings.containsKey(restaurantName)) {
            ratings.put(restaurantName, ratings.get(restaurantName) + 1);
        }
    }

}
