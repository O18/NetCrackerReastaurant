package com.sanik3d.restaurant.events;

import com.sanik3d.restaurant.presenter.callbacks.LoadInMemoryCallback;

/**
 * Created by 1 on 28.11.2016.
 */
public class LoadInMemoryEvent implements Event {
    private String path;
    private LoadInMemoryCallback callback;

    public LoadInMemoryEvent(String path,LoadInMemoryCallback callback) {
        this.path = path;
        this.callback = callback;
    }

    public String getPath() {
        return path;
    }

    public LoadInMemoryCallback getCallback() {
        return callback;
    }
}
