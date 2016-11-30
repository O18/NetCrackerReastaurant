package com.sanik3d.restaurant.eventbus.event;

import com.sanik3d.restaurant.eventbus.events.Event;
import com.sanik3d.restaurant.presenter.callbacks.AddCategoryCallback;
import com.sanik3d.restaurant.presenter.callbacks.SaveMenuCallback;
import com.sanik3d.restaurant.presenter.callbacks.SaveMunuCallback;

/**
 * Created by 1 on 14.11.2016.
 */
public class SaveMenuEvent implements Event {
    private String path;
    private SaveMenuCallback callback;
    public SaveMenuEvent(String path, SaveMenuCallback callback) {
        this.path = path;
        this.callback = callback;
    }

    public String getPath() {
        return path;
    }
}
