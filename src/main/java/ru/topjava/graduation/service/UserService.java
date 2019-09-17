package ru.topjava.graduation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.topjava.graduation.AuthorizedUser;
import ru.topjava.graduation.model.User;
import ru.topjava.graduation.repository.UserRepository;
import java.util.List;

import static ru.topjava.graduation.util.ValidationUtil.*;

@Service("userService")
public class UserService implements UserDetailsService {
    private UserRepository repository;

    public UserService() {
    }

    @Autowired
    public void setRepository(UserRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public User getByEmail(String email) {
        Assert.notNull(email, "email must not be null");
        return checkNotFound(repository.findByEmailIsLike(email), "email=" + email);
    }

    @Transactional
    public void delete(Integer id) {
        checkNotFoundWithId(repository.existsById(id), id);
        repository.deleteById(id);
    }

    @Transactional
    public User save(User user) {
        Assert.notNull(user, "user must not be null");
        return repository.save(user);
    }

    @Transactional
    public List<User> getAll() {
        return repository.findAll();
    }

    @Transactional
    public User update(User user) {
        Assert.notNull(user, "user must not be null");
        return checkIsNotFoundWithId(repository.save(user), user.getId());
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User userFound = repository.findByEmailIsLike(email.toLowerCase());
        if (userFound == null) {
            throw new UsernameNotFoundException("User " + email + " not found");
        }
        return new AuthorizedUser(userFound);
    }
}
