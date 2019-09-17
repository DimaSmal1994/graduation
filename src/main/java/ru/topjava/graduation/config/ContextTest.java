package ru.topjava.graduation.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.topjava.graduation.model.Dish;
import ru.topjava.graduation.model.Restaurant;
import ru.topjava.graduation.service.DishService;
import ru.topjava.graduation.service.RestaurantService;

import java.util.ArrayList;

public class ContextTest {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

//        for (String currentBean : context.getBeanDefinitionNames()) {
//            System.out.println(currentBean);
//        }

//        RestaurantRepository restaurantRepository = context.getBean(RestaurantRepository.class);
        RestaurantService restaurantService = context.getBean(RestaurantService.class);

        Restaurant restaurant = new Restaurant("QQ", new ArrayList<>());
        Restaurant savedRestaurant = restaurantService.save(restaurant);

//        DishRepository dishRepository = context.getBean(DishRepository.class);
        DishService dishService = context.getBean(DishService.class);
        int id = dishService.save(new Dish("steak", 800, savedRestaurant)).getId();
        System.out.println(dishService.getById(id));
    }
}
