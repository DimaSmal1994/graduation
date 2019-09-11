package ru.topjava.graduation.model;

public class Dish {
    private Integer id;
    private String name;
    private Integer price;
    private Restaurant restaurant;

    public Dish() {
    }

    public Dish(String name, Integer price, Restaurant restaurant) {
        this.name = name;
        this.price = price;
        this.restaurant = restaurant;
    }

    public Dish(Integer id, String name, Integer price, Restaurant restaurant) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.restaurant = restaurant;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
