package com.sanik3d.restaurant.eventbus.event;

/**
 * Created by 1 on 21.11.2016.
 */
public class EventDeleteDish implements Event{
    private String nameOfDish;
    public EventDeleteDish (String nameOfDish) {
        this.nameOfDish = nameOfDish;
    }
}
