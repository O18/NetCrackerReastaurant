package com.sanik3d.restaurant.model;

import java.io.Serializable;
import java.util.Set;

/**
 * Created by Александр on 06.11.2016.
 */
public class Category implements Serializable{
    private static final long serialVersionUID = 1L;

    private final String name;
    private Set<Dish> dishes;

    public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addDish(Dish dish){
        dishes.add(dish);
    }

    public Set<Dish> getDishes() {
        return dishes;
    }

    public void deleteDish(Dish dish){
        dishes.remove(dish);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        return name.equals(category.name);

    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
