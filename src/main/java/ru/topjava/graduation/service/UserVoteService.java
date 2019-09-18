package ru.topjava.graduation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.topjava.graduation.model.Restaurant;
import ru.topjava.graduation.model.UserVote;
import ru.topjava.graduation.repository.RestaurantRepository;
import ru.topjava.graduation.repository.UserRepository;
import ru.topjava.graduation.repository.UserVoteRepository;
import ru.topjava.graduation.to.UserVoteTo;

import java.time.LocalDate;
import java.util.List;

import static ru.topjava.graduation.util.ValidationUtil.*;

@Service
public class UserVoteService {

    private UserVoteRepository userVoteRepository;
    private UserRepository userRepository;
    private RestaurantRepository restaurantRepository;

    public UserVoteService() {
    }

    @Autowired
    public void setUserVoteRepository(UserVoteRepository userVoteRepository) {
        this.userVoteRepository = userVoteRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setRestaurantRepository(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Transactional
    public UserVoteTo save(UserVoteTo userVoteTo, int userId) {
        Assert.notNull(userVoteTo, "userVote must not be null");
        Restaurant chosenRestaurant = restaurantRepository.findById(userVoteTo.getChosenRestaurantId())
                .orElse(null);
        Assert.notNull(chosenRestaurant, "chosenRestaurant not found");
        UserVote userVote = UserVoteTo.createNewUserVoteFromTo(userVoteTo, chosenRestaurant);
        userVote.setUser(userRepository.getOne(userId));

        UserVote saved = checkIsNotFoundWithId(userVoteRepository.save(userVote), userId);
        return UserVoteTo.asToForUser(saved);
    }

    @Transactional
    public void delete(int id, int userId) {
        checkNotFoundWithId(userVoteRepository.deleteUserVoteByUserIsAndIdEquals(id, userId) != 0, id);
    }

    @Transactional
    public UserVote getById(int id, int userId) {
        return checkIsNotFoundWithId(userVoteRepository.findById(id, userId), id);
    }

    @Transactional
    public List<UserVote> getFilteredByUser(int userId) {
        return checkIsNotFoundWithId(userVoteRepository.findAllByUser(userId), userId);
    }

    @Transactional
    public List<UserVote> getAllOrderedByDate(int userId) {
        return checkIsNotFoundWithId(userVoteRepository.findAllOrderByDate(userId), userId);
    }


    @Transactional
    public UserVoteTo getTodaysVote(int userId) {
        UserVote todayVote = checkNotFound(userVoteRepository.findByDateEquals(LocalDate.now(), userId),
                "there is not todayVote for user with id=" + userId);

        return UserVoteTo.asToForUser(todayVote);
    }
}
