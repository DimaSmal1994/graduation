package ru.topjava.graduation.model;

public class RestaurantRating {
    private String restaurantName;
    private Integer rating;

    public RestaurantRating() {
    }

    public RestaurantRating(String restaurantName, Integer rating) {
        this.restaurantName = restaurantName;
        this.rating = rating;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}
