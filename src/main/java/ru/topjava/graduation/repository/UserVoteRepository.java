package ru.topjava.graduation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.topjava.graduation.model.UserVote;

public interface UserVoteRepository extends JpaRepository<UserVote, Integer> {

}
