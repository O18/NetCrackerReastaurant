package com.sanik3d.restaurant.events;

import com.sanik3d.restaurant.presenter.callbacks.ShowAllCategoriesCallback;

/**
 * Created by 1 on 29.11.2016.
 */
public class ShowAllCategoriesEvent implements Event {
    ShowAllCategoriesCallback callback;

    public ShowAllCategoriesEvent(ShowAllCategoriesCallback callback) {
        this.callback = callback;
    }

    public ShowAllCategoriesCallback getCallback() {
        return callback;
    }
}
