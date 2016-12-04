package com.sanik3d.restaurant.events;

import com.sanik3d.restaurant.presenter.callbacks.SaveMenuCallback;

/**
 * Created by 1 on 14.11.2016.
 */
public class SaveMenuEvent implements Event {
    private final String path;
    private final SaveMenuCallback callback;

    public SaveMenuEvent(String path, SaveMenuCallback callback) {
        this.path = path;
        this.callback = callback;
    }

    public String getPath() {
        return path;
    }

    public SaveMenuCallback getCallback() {
        return callback;
    }
}
