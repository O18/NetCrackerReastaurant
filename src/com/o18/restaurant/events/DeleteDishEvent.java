package com.o18.restaurant.events;

import com.o18.restaurant.eventbus.Event;
import com.o18.restaurant.presenter.Callback;

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
