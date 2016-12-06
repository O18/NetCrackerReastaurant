package com.sanik3d.restaurant.model.menuEvents;

import com.sanik3d.restaurant.model.Category;

import java.util.Set;

/**
 * Created by Александр on 06.12.2016.
 */
public class AddCategoriesMenuEvent implements MenuEvent {

    private final Set<Category> toAddCategories;

    public AddCategoriesMenuEvent(Set<Category> toAddCategories) {
        this.toAddCategories = toAddCategories;
    }

    public Set<Category> getToAddCategories() {
        return toAddCategories;
    }
}