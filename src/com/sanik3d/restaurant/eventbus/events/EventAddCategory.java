package com.sanik3d.restaurant.eventbus.events;

/**
 * Created by 1 on 14.11.2016.
 */
public class EventAddCategory implements Event {
    private String nameOfCategory;

    public EventAddCategory(String nameOfCategory) {
        this.nameOfCategory = nameOfCategory;
    }

    public String getNameOfCategory() {
        return nameOfCategory;
    }
}
