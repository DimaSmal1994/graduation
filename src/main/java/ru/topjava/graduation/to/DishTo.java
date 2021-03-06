package ru.topjava.graduation.to;

import org.hibernate.validator.constraints.NotBlank;
import ru.topjava.graduation.HasId;
import ru.topjava.graduation.model.Dish;
import ru.topjava.graduation.model.Restaurant;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class DishTo implements Serializable, HasId {

    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotBlank
    private String name;

    @NotNull
    private Integer price;

    @NotNull
    private Integer restaurantId;

    public DishTo() {

    }

    public DishTo (Integer id, String name, int price, int restaurantId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.restaurantId = restaurantId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    @Override
    public String toString() {
        return "DishTo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", restaurantId=" + restaurantId +
                '}';
    }

    public static Dish createNewDishFromTo(DishTo dishTo, Restaurant restaurant) {
        return new Dish(dishTo.getId(), dishTo.getName(),
                dishTo.getPrice(), restaurant);
    }

    public static DishTo asTo(Dish dish) {
        return new DishTo(dish.getId(), dish.getName(), dish.getPrice(), dish.getRestaurant().getId());
    }

    public static List<DishTo> asToList(List<Dish> dishList) {
        return dishList.stream()
                .map(DishTo::asTo)
                .collect(Collectors.toList());
    }
}
