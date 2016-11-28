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

    MenuCash(Menu menu) {
        this.menu = menu;
        nameCategoryMap = new HashMap<>();
    }

    void addSetCategories(Set<Category> categories){
        for (Category category : categories) {
            nameCategoryMap.put(category.getName(), category);
        }
    }

    void addCategory(Category category){
        nameCategoryMap.put(category.getName(), category);
    }
}
