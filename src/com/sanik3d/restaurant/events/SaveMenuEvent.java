package com.sanik3d.restaurant.events;

import com.sanik3d.restaurant.presenter.Callback;

/**
 * Created by 1 on 14.11.2016.
 */
public class SaveMenuEvent implements Event {
    private final String path;
    private final Callback callback;

    public SaveMenuEvent(String path, Callback callback) {
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
