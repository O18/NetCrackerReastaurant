package com.sanik3d.restaurant.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Александр on 05.11.2016.
 */
public class Menu implements Serializable {
    private static final long serialVersionUID = 1L;

    private Set<Category> categories;
    private MenuCash cash;//TODO: rename

    public Menu() {
        cash = new MenuCash(this);
        categories = new HashSet<>();
    }

    public Menu(Set<Category> categories) {
        this.categories = categories;
        cash = new MenuCash(this);
        cash.addSetCategories(categories);//TODO: rename
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
        cash = new MenuCash(this);
        cash.addSetCategories(categories);
    }

    public void addDish(Dish dish) {
        Category categoryOfAddingDish = dish.getCategory();
        categoryOfAddingDish.addDish(dish);
    }

    public void deleteDish(Dish dish) {
        Category categoryOfDeletingDish = dish.getCategory();
        categoryOfDeletingDish.deleteDish(dish);
    }

    public void addCategory(Category category) {
        categories.add(category);
        cash.addCategory(category);
    }

    public void deleteCategory(Category category) {
        categories.remove(category);
        cash.deleteCategory(category);
    }

    public Map<String, Category> getNameCategoryMap() {
        return cash.getNameCategoryMap();
    }

    public Map<String, Dish> getNameDishMap() {
        return cash.getNameDishMap();
    }

    public Set<Dish> getDishes(){
        Set<Dish> dishSet = new HashSet<>();
        for(Category category: categories){
            dishSet.addAll(category.getDishes());
        }

        return dishSet;
    }
}
