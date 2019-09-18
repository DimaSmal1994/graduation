package ru.topjava.graduation.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.topjava.graduation.AuthorizedUser;
import ru.topjava.graduation.model.Restaurant;
import ru.topjava.graduation.model.UserVote;
import ru.topjava.graduation.service.RestaurantService;
import ru.topjava.graduation.service.UserVoteService;
import ru.topjava.graduation.to.UserVoteTo;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static ru.topjava.graduation.util.ValidationUtil.*;

@RestController
@RequestMapping(value = UserController.PROFILE_URL)
public class UserController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    static final String PROFILE_URL = "profile";

    private UserVoteService userVoteService;

    private RestaurantService restaurantService;

    @Autowired
    public void setUserVoteService(UserVoteService userVoteService) {
        this.userVoteService = userVoteService;
    }

    @Autowired
    public void setRestaurantService(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping(value = "/restaurants", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Restaurant>> getAllRestaurants() {
        LOG.info("get all restaurants for user with id={}", AuthorizedUser.id());
        List<Restaurant> restaurants = restaurantService.getAll();

        return ResponseEntity.ok(restaurants);
    }

    @PostMapping(value = "/vote", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserVoteTo> doVote(@Valid @RequestBody UserVoteTo userVoteTo){
        checkNew(userVoteTo);
        checkDateConsistent(userVoteTo);
        checkTimeConsistentForSave(userVoteTo);

        int userId = AuthorizedUser.id();
        LOG.info("create user vote {} for user with id={}", userVoteTo, userId);
        UserVoteTo vote = userVoteService.save(userVoteTo, userId);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(PROFILE_URL + "/vote")
                .buildAndExpand(vote.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(vote);
    }

    @DeleteMapping(value = "/vote")
    public ResponseEntity<UserVote> delete(){
        int userId = AuthorizedUser.id();
        UserVoteTo vote = userVoteService.getTodaysVote(userId);
        checkTimeConsistentForDelete();
        LOG.info("delete vote {} from user with id={}", vote, userId);
        userVoteService.delete(vote.getId(), userId);

        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/vote", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserVoteTo> getTodayVote(){
        int userId = AuthorizedUser.id();
        LOG.info("get today's vote for user with id={}", userId);
        UserVoteTo userVote = userVoteService.getTodaysVote(userId);

        return ResponseEntity.ok(userVote);
    }

}
