package ru.topjava.graduation.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "user_votes", uniqueConstraints = {@UniqueConstraint(
        name = "user_votes_unique_user_date_idx", columnNames = {"user_id", "date"})})
public class UserVote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "date", columnDefinition = "DATE DEFAULT CURRENT_DATE")
    private LocalDate date;

    @Column(name = "time", columnDefinition = "TIME DEFAULT CURRENT_TIME")
    private LocalTime time;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chosen_restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    @JoinColumn(name = "user_id", nullable = false)
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

    @Override
    public String toString() {
        return "UserVote{" +
                "id=" + id +
                ", date=" + date +
                ", time=" + time +
                ", restaurant=" + restaurant +
                ", user=" + user +
                '}';
    }
}
