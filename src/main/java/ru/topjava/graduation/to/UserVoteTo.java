package ru.topjava.graduation.to;

import ru.topjava.graduation.HasId;
import ru.topjava.graduation.model.Restaurant;
import ru.topjava.graduation.model.UserVote;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

public class UserVoteTo implements Serializable, HasId {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private LocalDate date;

    private LocalTime time;

    @NotNull
    private Integer chosenRestaurantId;

    private Integer userId;

    public UserVoteTo() {

    }

    public UserVoteTo(Integer id, LocalDate date, LocalTime time, Integer chosenRestaurantId, Integer userId) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.chosenRestaurantId = chosenRestaurantId;
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getChosenRestaurantId() {
        return chosenRestaurantId;
    }

    public void setChosenRestaurantId(Integer chosenRestaurantId) {
        this.chosenRestaurantId = chosenRestaurantId;
    }

    @Override
    public Integer getId() {
        return null;
    }

    @Override
    public void setId(Integer id) {

    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public static UserVote createNewUserVoteFromTo(UserVoteTo userVoteTo, Restaurant restaurant) {
        return new UserVote(userVoteTo.getId(), userVoteTo.getDate(), userVoteTo.getTime(), restaurant);
    }

    public static UserVoteTo asToForAdmin(UserVote userVote) {
        return new UserVoteTo(userVote.getId(), userVote.getDate(), userVote.getTime(), userVote.getRestaurant().getId(), userVote.getUser().getId());
    }
    public static UserVoteTo asToForUser(UserVote userVote) {
        return new UserVoteTo(userVote.getId(), userVote.getDate(), userVote.getTime(), userVote.getRestaurant().getId(), null);
    }

    public static List<UserVoteTo> asToList(List<UserVote> userVoteList) {
        return userVoteList.stream()
                .map(UserVoteTo::asToForAdmin)
                .collect(Collectors.toList());
    }
}
