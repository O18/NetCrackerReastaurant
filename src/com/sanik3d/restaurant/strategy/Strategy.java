package com.sanik3d.restaurant.strategy;


import com.sanik3d.restaurant.events.Event;

/**
 * Created by 1 on 04.12.2016.
 */
public interface Strategy {
    Event createEvent(String[] commandString);
}
