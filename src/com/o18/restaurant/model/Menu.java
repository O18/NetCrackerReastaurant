package com.o18.restaurant.model;

import com.o18.restaurant.eventbus.EventBus;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Александр on 05.11.2016.
 */
//todo tdo, которое будет пересылаться
public class Menu extends EventBus implements Serializable {//todo разнести по 3 артифактам все классы: клиент, сервер, коммон-шаред
    private static final long serialVersionUID = -2922673041785815304L;

    private Set<Category> categories;

    public Menu() {
        categories = new HashSet<>();
        handlers = new HashMap<>();
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Set<Category> getCategories() {
        return Collections.unmodifiableSet(categories);
    }

    public boolean addDishToCategory(Dish dish, Category category) {
        if(!categories.contains(category)) return false;

        if(category.addDish(dish)){
            post(new MenuChangedEvent());
            return true;
        }

        return false;
    }

    public boolean deleteDishFromCategory(Dish dish, Category category) {
        if(category.deleteDish(dish)){
            post(new MenuChangedEvent());
            return true;
        }

        return false;
    }

    public boolean addCategory(Category category) {
        if(categories.add(category)) {
            post(new MenuChangedEvent());
            return true;
        }

        return false;
    }

    public boolean deleteCategory(Category category) {
        if(categories.remove(category)){
            post(new MenuChangedEvent());
            return true;
        }

        return false;
    }

    public boolean setCategoryName(Category category, String newName){
        if(category.setName(newName)){
            post(new MenuChangedEvent());
            return true;
        }

        return false;
    }

    public boolean setDishName(Dish dish, Category category, String newName){
        if(!categories.contains(category)) return false;

        if(category.getDishes().contains(dish)){
            dish.setName(newName);
            post(new MenuChangedEvent());
            return true;
        }

        return false;
    }

    public boolean setDishCost(Dish dish, Category category, double newCost){
        if(!categories.contains(category)) return false;

        if(category.getDishes().contains(dish)){
            dish.setCost(newCost);
            post(new MenuChangedEvent());
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

    @Override
    public String toString() {
        return "Menu{" +
                "categories=" + categories +
                '}';
    }
}
