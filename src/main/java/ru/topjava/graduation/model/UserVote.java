package ru.topjava.graduation.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class UserVote {
    private Integer id;
    private LocalDate date;
    private LocalTime time;
    private Restaurant restaurant;
    private User user;

    public UserVote() {
    }

    public UserVote(LocalDate date, LocalTime time, Restaurant restaurant, User user) {
        this.date = date;
        this.time = time;
        this.restaurant = restaurant;
        this.user = user;
    }

    public UserVote(Integer id, LocalDate date, LocalTime time, Restaurant restaurant, User user) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.restaurant = restaurant;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
