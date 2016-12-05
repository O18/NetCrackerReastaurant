package com.sanik3d.restaurant.events;

import com.sanik3d.restaurant.presenter.callbacks.ShowHelpCallback;

/**
 * Created by Александр on 05.12.2016.
 */
public class HelpShowEvent implements Event{
    private final ShowHelpCallback callback;

    public HelpShowEvent(ShowHelpCallback callback) {
        this.callback = callback;
    }

    public ShowHelpCallback getCallback() {
        return callback;
    }
}
