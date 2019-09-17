package ru.topjava.graduation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.topjava.graduation.model.UserVote;
import ru.topjava.graduation.repository.UserRepository;
import ru.topjava.graduation.repository.UserVoteRepository;

import java.util.List;

import static ru.topjava.graduation.util.ValidationUtil.*;

@Service
public class UserVoteService {

    private UserVoteRepository userVoteRepository;
    private UserRepository userRepository;

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

    @Transactional
    public UserVote save(UserVote userVote, int userId) {
        Assert.notNull(userVote, "userVote must not be null");
        userVote.setUser(userRepository.getOne(userId));
        return checkIsNotFoundWithId(userVoteRepository.save(userVote), userId);
    }

    @Transactional
    public void delete(int id, int userId) {
        checkNotFoundWithId(userVoteRepository.deleteUserVoteByUserIsAndIdEquals(id, userId)!=0, id);
    }

    @Transactional
    public UserVote getById(int id, int userId){
        return checkIsNotFoundWithId(userVoteRepository.findById(id, userId), id);
    }

    @Transactional
    public List<UserVote> getFilteredByUser(int userId){
        return checkIsNotFoundWithId(userVoteRepository.findAllByUser(userId), userId);
    }

    @Transactional
    public List<UserVote> getAllOrderedByDate(int userId){
        return checkIsNotFoundWithId(userVoteRepository.findAllOrderByDate(userId), userId);
    }



}
