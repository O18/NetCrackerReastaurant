package com.sanik3d.restaurant.model;

import java.io.Serializable;
import java.util.Set;

/**
 * Created by Александр on 05.11.2016.
 */
public class Menu implements Serializable{
    private static final long serialVersionUID = 1L;

    private Set<Dish> dishes;
    private Set<Category> categories;

    public Menu() {
    }

    public Menu(Set<Dish> dishes, Set<Category> categories) {
        this.dishes = dishes;
        this.categories = categories;
    }

    public Set<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(Set<Dish> dishes) {
        this.dishes = dishes;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public boolean addDish(Dish dish){
        return dishes.add(dish);
    }

    public boolean addCategory(Category category){
        return categories.add(category);
    }
}
