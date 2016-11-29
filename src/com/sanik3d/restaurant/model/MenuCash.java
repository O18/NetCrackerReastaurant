package com.sanik3d.restaurant.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Александр on 29.11.2016.
 */
class MenuCash {
    private final Menu menu;
    private final Map<String, Category> nameCategoryMap;
    private final Map<String, Dish> nameDishMap;

    MenuCash(Menu menu) {
        this.menu = menu;
        nameCategoryMap = new HashMap<>();
        nameDishMap = new HashMap<>();
    }

    void addSetCategories(Set<Category> categories){
        for (Category category : categories) {
            addCategory(category);
        }
    }

    void addCategory(Category category){
        nameCategoryMap.put(category.getName(), category);
        for (Dish dish : category.getDishes()) {
            nameDishMap.put(dish.getName(), dish);
        }
    }

    void deleteCategory(Category category){
        nameCategoryMap.remove(category.getName(), category);
        for (Dish dish: category.getDishes()){
            nameDishMap.remove(dish.getName());
        }
    }

    Map<String, Category> getNameCategoryMap() {
        return nameCategoryMap;
    }

    Map<String, Dish> getNameDishMap(){
        return nameDishMap;
    }
}