package ru.topjava.graduation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.topjava.graduation.model.Restaurant;
import ru.topjava.graduation.repository.RestaurantRepository;
import ru.topjava.graduation.util.ValidationUtil;

import java.util.List;

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
        ValidationUtil.checkNotFoundWithId(repository.existsById(id), id);
        repository.deleteById(id);

    }

    @Transactional
    public List<Restaurant> getAll() {
        return repository.findAll();
    }

    @Transactional
    public Restaurant getByName(String name) {
        Restaurant result;
        if (name != null) {
            result = repository.findByNameIsLike(name);
        } else {
            result = null;
        }

        return result;
    }

    @Transactional
    public void update(Restaurant restaurant) {
        if (restaurant != null) {
            repository.save(restaurant);
        }
    }
}
