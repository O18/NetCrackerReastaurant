package com.sanik3d.restaurant.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Александр on 06.11.2016.
 */
public class Category implements Serializable{
    private static final long serialVersionUID = -2408931504592493532L;

    private final String name;
    private final Set<Dish> dishes;

    public Category(String name) {
        this.name = name;
        dishes = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public boolean addDish(Dish dish){
        return dishes.add(dish);
    }

    public Set<Dish> getDishes() {
        return Collections.unmodifiableSet(dishes);
    }

    public boolean deleteDish(Dish dish){
        return dishes.remove(dish);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        if (!name.equals(category.name)) return false;
        return dishes.equals(category.dishes);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return name;
    }
}
