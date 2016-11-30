package com.sanik3d.restaurant.eventbus.event;

import com.sanik3d.restaurant.eventbus.events.Event;
import com.sanik3d.restaurant.presenter.callbacks.AddCategoryCallback;
import com.sanik3d.restaurant.presenter.callbacks.LoadInMemmoryCallback;
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
}
