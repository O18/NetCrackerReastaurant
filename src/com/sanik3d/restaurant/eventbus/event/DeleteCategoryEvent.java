package com.sanik3d.restaurant.eventbus.event;


import com.sanik3d.restaurant.eventbus.events.Event;
import com.sanik3d.restaurant.presenter.callbacks.DeleteCategoryCallback;


/**
 * Created by 1 on 28.11.2016.
 */
public class DeleteCategoryEvent implements Event {
    private String nameOfCategory;
    private DeleteCategoryCallback callback;
    public DeleteCategoryEvent (String nameOfCategory,DeleteCategoryCallback callback) {
        this.nameOfCategory = nameOfCategory;
        this.callback = callback;
    }

    public String getNameOfCategory() {
        return nameOfCategory;
    }
}