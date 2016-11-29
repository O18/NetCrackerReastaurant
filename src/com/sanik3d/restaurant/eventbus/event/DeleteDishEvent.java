package com.sanik3d.restaurant.eventbus.event;

import com.sanik3d.restaurant.presenter.callbacks.AddCategoryCallback;
import com.sanik3d.restaurant.presenter.callbacks.DeleteDishCallback;

/**
 * Created by 1 on 21.11.2016.
 */
public class DeleteDishEvent implements Event{
    private String nameOfDish;
    private DeleteDishCallback callback;
    public DeleteDishEvent(String nameOfDish,DeleteDishCallback callback) {
        this.nameOfDish = nameOfDish;
        this.callback = callback;
    }
}
