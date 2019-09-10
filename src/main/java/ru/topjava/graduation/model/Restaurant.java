package ru.topjava.graduation.model;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String name;
    private List<Dish> menu;

    public Restaurant() {
    }

    public Restaurant(String name) {
        this.name = name;
        this.menu = new ArrayList<>();
    }

    public List<Dish> getMenu() {
        return menu;
    }

    public void setMenu(List<Dish> menu) {
        this.menu = menu;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
