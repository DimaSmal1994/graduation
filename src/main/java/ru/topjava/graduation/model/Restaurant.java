package ru.topjava.graduation.model;

import java.util.List;

public class Restaurant {
    private String name;
    private List<Dish> menu;

    public List<Dish> getMenu() {
        return menu;
    }

    public void setMenu(List<Dish> menu) {
        this.menu = menu;
    }
}
