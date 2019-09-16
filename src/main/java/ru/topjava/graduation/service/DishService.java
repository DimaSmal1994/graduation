package ru.topjava.graduation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.topjava.graduation.model.Dish;
import ru.topjava.graduation.repository.DishRepository;
import java.util.List;

import static ru.topjava.graduation.util.ValidationUtil.*;

@Service
public class DishService {
    private DishRepository repository;

    public DishService() {
    }

    @Autowired
    public void setRepository(DishRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Dish save(Dish dish) {
        Assert.notNull(dish, "dish must not be null");
        return repository.save(dish);
    }

    @Transactional
    public Dish getById(Integer id){
        return checkIsNotFoundWithId(repository.findById(id).orElse(null), id);
    }

    @Transactional
    public List<Dish> getAll(){
        return repository.findAll();
    }

    @Transactional
    public void delete(Integer id){
        checkNotFoundWithId(repository.existsById(id), id);
        repository.deleteById(id);
    }

    @Transactional
    public Dish update(Dish dish){
        Assert.notNull(dish, "dish must not be null");
        return repository.save(dish);
    }


}
