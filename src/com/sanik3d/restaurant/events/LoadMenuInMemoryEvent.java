package com.sanik3d.restaurant.events;

import com.sanik3d.restaurant.presenter.callbacks.LoadMenuInMemoryCallback;

/**
 * Created by 1 on 28.11.2016.
 */
public class LoadMenuInMemoryEvent implements Event {
    private final String path;
    private final LoadMenuInMemoryCallback callback;

    public LoadMenuInMemoryEvent(String path, LoadMenuInMemoryCallback callback) {
        this.path = path;
        this.callback = callback;
    }

    public String getPath() {
        return path;
    }

    public LoadMenuInMemoryCallback getCallback() {
        return callback;
    }
}
