package ru.topjava.graduation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.topjava.graduation.model.UserVote;

import java.util.List;

public interface UserVoteRepository extends JpaRepository<UserVote, Integer> {
    @Query(value = "delete from UserVote uv where uv.id=:id and uv.user.id=:userId")
    int deleteUserVoteByUserIsAndIdEquals(@Param(value = "id") int id, @Param(value = "userId") int userId);

    @Query(value = "select uv from UserVote uv left join fetch uv.user as users where uv.user.id=:userId")
    List<UserVote> findAllByUser(@Param(value = "userId") int userId);

    @Query(value = "select uv from UserVote uv where uv.id=:id and uv.user.id=:userId")
    UserVote findById(@Param(value = "id") int id, @Param(value = "userId") int userId);

    @Query(value = "select uv from UserVote uv LEFT JOIN FETCH uv.user as users LEFT JOIN FETCH uv.restaurant AS" +
            " restaurants where uv.user.id=:userId order by uv.date")
    List<UserVote> findAllOrderByDate(@Param("userId") int userId);
}
