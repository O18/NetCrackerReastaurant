package com.sanik3d.restaurant.events;

import com.sanik3d.restaurant.presenter.callbacks.AddCategoryCallback;

/**
 * Created by 1 on 28.11.2016.
 */
public class AddCategoryEvent implements Event {//TODO: final поля
    private String nameOfCategory;
    private AddCategoryCallback callback;

    public AddCategoryEvent(String nameOfCategory,AddCategoryCallback callback) {
        this.nameOfCategory = nameOfCategory;
        this.callback = callback;
    }

    public String getNameOfCategory() {
        return nameOfCategory;
    }

    public AddCategoryCallback getCallback() {
        return callback;
    }
}
