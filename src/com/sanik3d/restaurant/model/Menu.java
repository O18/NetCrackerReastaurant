package com.sanik3d.restaurant.model;

import java.io.Serializable;
import java.util.*;

/**
 * Created by Александр on 05.11.2016.
 */
public class Menu implements Serializable {
    private static final long serialVersionUID = 1L;

    private Set<Category> categories;

    public Menu() {
        categories = new HashSet<>();
    }

    public Menu(Set<Category> categories) {
        this.categories = categories;
    }

    public Set<Category> getCategories() {
        return Collections.unmodifiableSet(categories);
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public boolean addDish(Dish dish) {
        Category categoryOfAddingDish = dish.getCategory();
        return categoryOfAddingDish.addDish(dish);
    }

    public boolean deleteDish(Dish dish) {
        Category categoryOfDeletingDish = dish.getCategory();
        return categoryOfDeletingDish.deleteDish(dish);
    }

    public boolean addCategory(Category category) {
        return categories.add(category);
    }

    public boolean deleteCategory(Category category) {
        return categories.remove(category);
    }

    public Set<Dish> getDishes(){
        Set<Dish> dishSet = new HashSet<>();
        for(Category category: categories){
            dishSet.addAll(category.getDishes());
        }

        return Collections.unmodifiableSet(dishSet);
    }
}
