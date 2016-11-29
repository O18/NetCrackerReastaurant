package com.sanik3d.restaurant.events;

import com.sanik3d.restaurant.eventbus.events.Event;
import com.sanik3d.restaurant.presenter.callbacks.LoadInMemmoryCallback;

/**
 * Created by 1 on 28.11.2016.
 */
public class LoadInMemoryEvent implements Event {
    private String path;
    private LoadInMemmoryCallback callback;

    public LoadInMemoryEvent(String path,LoadInMemmoryCallback callback) {
        this.path = path;
        this.callback = callback;
    }

    public String getPath() {
        return path;
    }
}
