package com.sanik3d.restaurant.model;

/**
 * Created by Александр on 06.12.2016.
 */
public interface MenuListener {

    void onAddCategory(Category category);

    void onDeleteCategory(Category category);

    void onAddDish(Dish dish);

    void onDeleteDish(Dish dish);
}
