package model;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Александр on 05.11.2016.
 */

public class Menu implements Serializable {
    private static final long serialVersionUID = -2922673041785815304L;

    private Set<Category> categories;

    public Menu() {
        categories = new HashSet<>();
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Set<Category> getCategories() {
        return Collections.unmodifiableSet(categories);
    }

    public boolean addDishToCategory(Dish dish, Category category) {
        return categories.contains(category) & category.addDish(dish);

    }

    public boolean deleteDishFromCategory(Dish dish, Category category) {
        return categories.contains(category) & category.deleteDish(dish);

    }

    public boolean addCategory(Category category) {
        return categories.add(category);

    }

    public boolean deleteCategory(Category category) {
        return categories.remove(category);

    }

    public boolean setCategoryName(Category category, String newName){
        return category.setName(newName);

    }

    public boolean setDishName(Dish dish, Category category, String newName){
        if(!categories.contains(category)) return false;

        if(category.getDishes().contains(dish)){
            dish.setName(newName);
            return true;
        }

        return false;
    }

    public boolean setDishCost(Dish dish, Category category, double newCost){
        if(!categories.contains(category)) return false;

        if(category.getDishes().contains(dish)){
            dish.setCost(newCost);
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
