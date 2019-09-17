package ru.topjava.graduation.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.topjava.graduation.AuthorizedUser;
import ru.topjava.graduation.model.Restaurant;
import ru.topjava.graduation.service.RestaurantService;
import ru.topjava.graduation.service.UserVoteService;

import java.util.List;

@RestController
@RequestMapping(value = UserController.PROFILE_URL)
public class UserController {
    //TODO ДОПИЛИТЬ КОНТРОЛЛЕР ДЛЯ ЮЗЕРА

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    static final String PROFILE_URL = "rest/profile";

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
}
