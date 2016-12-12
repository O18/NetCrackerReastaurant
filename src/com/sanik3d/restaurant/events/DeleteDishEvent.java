package com.sanik3d.restaurant.events;

import com.sanik3d.restaurant.presenter.Callback;

/**
 * Created by 1 on 21.11.2016.
 */
public class DeleteDishEvent implements Event {
    private final String nameOfDish;
    private final Callback callback;

    public DeleteDishEvent(String nameOfDish, Callback callback) {
        this.nameOfDish = nameOfDish;
        this.callback = callback;
    }

    public String getNameOfDish() {
        return nameOfDish;
    }

    public Callback getCallback() {
        return callback;
    }
}
