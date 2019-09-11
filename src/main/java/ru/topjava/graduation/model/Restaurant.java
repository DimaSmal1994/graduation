package ru.topjava.graduation.model;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private Integer id;
    private String name;
    private List<Dish> menu;

    public Restaurant() {
    }

    public Restaurant(Integer id, String name, List<Dish> menu) {
        this.id = id;
        this.name = name;
        this.menu = menu;
    }

    public Restaurant(String name, List<Dish> menu) {
        this.name = name;
        this.menu = menu;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
