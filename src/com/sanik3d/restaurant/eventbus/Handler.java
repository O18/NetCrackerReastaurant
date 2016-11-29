package com.sanik3d.restaurant.eventbus;

import com.sanik3d.restaurant.events.Event;

/**
 * Created by Александр on 24.11.2016.
 */
public interface Handler {

    void handle(Event event);
}
