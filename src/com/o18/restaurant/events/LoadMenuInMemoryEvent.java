package com.o18.restaurant.events;

import com.o18.restaurant.presenter.Callback;

/**
 * Created by 1 on 28.11.2016.
 */
public class LoadMenuInMemoryEvent implements Event {
    private final String path;
    private final Callback callback;

    public LoadMenuInMemoryEvent(String path, Callback callback) {
        this.path = path;
        this.callback = callback;
    }

    public String getPath() {
        return path;
    }

    public Callback getCallback() {
        return callback;
    }
}
