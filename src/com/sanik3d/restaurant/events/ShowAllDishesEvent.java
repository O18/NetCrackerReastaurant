package com.sanik3d.restaurant.events;

import com.sanik3d.restaurant.presenter.callbacks.ShowAllDishesCallback;

/**
 * Created by 1 on 29.11.2016.
 */
public class ShowAllDishesEvent implements Event {
    private final ShowAllDishesCallback callback;

    public ShowAllDishesEvent(ShowAllDishesCallback callback) {
        this.callback = callback;
    }

    public ShowAllDishesCallback getCallback() {
        return callback;
    }
}
