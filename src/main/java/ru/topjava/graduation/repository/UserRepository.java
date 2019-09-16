package ru.topjava.graduation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.topjava.graduation.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByNameIsLike(String name);

    User findByEmailIsLike(String email);

}
