package com.sanik3d.restaurant.eventbus.event;

import com.sanik3d.restaurant.eventbus.events.Event;
import com.sanik3d.restaurant.presenter.callbacks.ShowAllDishesCallback;

/**
 * Created by 1 on 29.11.2016.
 */
public class ShowAllDishesEvent implements Event {
    ShowAllDishesCallback callback;
    public ShowAllDishesEvent(ShowAllDishesCallback callback) {
        this.callback = callback;
    }
}
