package com.sanik3d.restaurant.eventbus.events;

/**
 * Created by 1 on 21.11.2016.
 */
public class EventDeleteCategory implements Event {
    private String nameOfCategory;

    public EventDeleteCategory (String nameOfCategory) {
        this.nameOfCategory = nameOfCategory;
    }

    public String getNameOfCategory() {
        return nameOfCategory;
    }

}
