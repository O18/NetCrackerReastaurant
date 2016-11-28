package com.sanik3d.restaurant.eventbus.events;

/**
 * Created by 1 on 14.11.2016.
 */
public class EventSave implements Event {
    private String path;
    public EventSave (String path) { this.path = path; }

    public String getPath() {
        return path;
    }
}
