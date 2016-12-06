package com.sanik3d.restaurant.model.menuEvents;

import com.sanik3d.restaurant.model.Dish;

import java.util.Set;

/**
 * Created by Александр on 06.12.2016.
 */
public class AddDishesMenuEvent implements MenuEvent {
    private final Set<Dish> toAddDishes;

    public AddDishesMenuEvent(Set<Dish> toAddDishes) {
        this.toAddDishes = toAddDishes;
    }

    public Set<Dish> getToAddDishes() {
        return toAddDishes;
    }
}
