package ru.topjava.graduation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.topjava.graduation.model.Dish;
import ru.topjava.graduation.model.Restaurant;
import ru.topjava.graduation.repository.DishRepository;
import ru.topjava.graduation.repository.RestaurantRepository;
import ru.topjava.graduation.to.DishTo;
import ru.topjava.graduation.util.exception.NotFoundException;

import java.util.List;
import java.util.Optional;

import static ru.topjava.graduation.util.ValidationUtil.checkIsNotFoundWithId;
import static ru.topjava.graduation.util.ValidationUtil.checkNotFoundWithId;

@Service
public class DishService {
    private DishRepository dishRepository;

    private RestaurantRepository restaurantRepository;

    public DishService() {
    }

    @Autowired
    public void setDishRepository(DishRepository repository) {
        this.dishRepository = repository;
    }

    @Transactional
    public DishTo save(DishTo dishTo) {
        Assert.notNull(dishTo, "dish must not be null");
        Restaurant restaurant = restaurantRepository.findById(dishTo.getRestaurantId()).orElse(null);
        Dish dish = DishTo.createNewDishFromTo(dishTo, checkIsNotFoundWithId(restaurant, dishTo.getRestaurantId()));

        return DishTo.asTo(dishRepository.save(dish));
    }

    @Transactional
    public DishTo getById(Integer id){
        Optional<Dish> byId = dishRepository.findById(id);
        Dish dish;
        if (byId.isPresent()){
            dish = checkIsNotFoundWithId(byId.get(), id);
        } else {
            throw new NotFoundException("Dish with id="+id+" is not found");
        }

        return DishTo.asTo(dish);
    }

    @Transactional
    public List<Dish> getAll(){
        return dishRepository.findAll();
    }

    @Transactional
    public void delete(Integer id){
        checkNotFoundWithId(dishRepository.existsById(id), id);
        dishRepository.deleteById(id);
    }

    @Transactional
    public DishTo update(DishTo dishTo){
        Assert.notNull(dishTo, "dishTo must not be null");
        checkIsNotFoundWithId(dishTo, dishTo.getId());

        Dish dishToUpdate = DishTo.createNewDishFromTo(dishTo,
                restaurantRepository.findById(dishTo.getRestaurantId()).orElse(null));
        Dish savedDish = dishRepository.save(dishToUpdate);

        return DishTo.asTo(savedDish);
    }

    @Transactional
    public List<DishTo> getAllByRestaurantId(int id){
        List<DishTo> result = DishTo.asToList(dishRepository.getAllByRestaurantId(id));
        return checkIsNotFoundWithId(result, id);
    }


}
