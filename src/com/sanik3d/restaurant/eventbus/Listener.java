package com.sanik3d.restaurant.eventbus;

import com.sanik3d.restaurant.eventbus.event.Event;

/**
 * Created by Александр on 12.11.2016.
 */
public interface Listener {
    void onEvent(Event e);
}
