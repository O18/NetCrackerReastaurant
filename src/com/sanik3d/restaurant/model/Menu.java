package com.sanik3d.restaurant.model;

import java.io.Serializable;
import java.util.*;

/**
 * Created by Александр on 05.11.2016.
 */
public class Menu implements Serializable {
    private static final long serialVersionUID = -2922673041785815304L;

    private Set<Category> categories;
    private Set<MenuListener> listeners;

    public Menu() {
        categories = new HashSet<>();
        listeners = new HashSet<>();
    }

    public Set<Category> getCategories() {
        return Collections.unmodifiableSet(categories);
    }

    public boolean addDishToCategory(Dish dish, Category category) {
        if(!categories.contains(category)) return false;

        if(category.addDish(dish)){
            for (MenuListener listener : listeners) {
                listener.onAddDish(dish);
            }
            return true;
        }

        return false;
    }

    public boolean deleteDishFromCategory(Dish dish, Category category) {
        if(category.deleteDish(dish)){
            for (MenuListener listener : listeners) {
                listener.onDeleteDish(dish);
            }
            return true;
        }

        return false;
    }

    public boolean addCategory(Category category) {
        if(categories.add(category)) {
            for (MenuListener listener : listeners) {
                listener.onAddCategory(category);
            }
            return true;
        }

        return false;
    }

    public boolean deleteCategory(Category category) {
        if(categories.remove(category)){
            for (MenuListener listener : listeners) {
                listener.onAddCategory(category);
            }

            return true;
        }

        return false;
    }

    public Set<Dish> getDishes(){
        Set<Dish> dishSet = new HashSet<>();
        for(Category category: categories){
            dishSet.addAll(category.getDishes());
        }

        return Collections.unmodifiableSet(dishSet);
    }

    public void addListener(MenuListener listener){
        listeners.add(listener);
    }

    public void deleteListener(MenuListener listener){
        listeners.remove(listener);
    }
}
