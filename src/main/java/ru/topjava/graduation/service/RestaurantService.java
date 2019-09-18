package ru.topjava.graduation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.topjava.graduation.model.Restaurant;
import ru.topjava.graduation.repository.RestaurantRepository;

import java.util.List;

import static ru.topjava.graduation.util.ValidationUtil.*;

@Service
public class RestaurantService {

    private RestaurantRepository repository;

    @Autowired
    public void setRepository(RestaurantRepository repository) {
        this.repository = repository;
    }

    public RestaurantService() {
    }

    @Transactional
    public Restaurant save(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        return repository.save(restaurant);
    }

    @Transactional
    public void delete(Integer id) {
        checkNotFoundWithId(repository.existsById(id), id);
        repository.deleteById(id);
    }

    @Transactional
    public List<Restaurant> getAll() {
        return repository.findAll();
    }

    @Transactional
    public Restaurant getByName(String name) {
        Assert.notNull(name, "restaurant name must not be null");
        return checkNotFound(repository.findByNameIsLike(name), "with name=" + name);
    }

    @Transactional
    public Restaurant update(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        return checkIsNotFoundWithId(repository.save(restaurant), restaurant.getId());
    }

    @Transactional
    public Restaurant getById(int id) {
        Restaurant restaurant = repository.findById(id).orElse(null);
        return checkIsNotFoundWithId(restaurant, id);
    }
}
