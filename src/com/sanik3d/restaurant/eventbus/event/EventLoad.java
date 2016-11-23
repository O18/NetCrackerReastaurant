package com.sanik3d.restaurant.eventbus.event;

/**
 * Created by 1 on 14.11.2016.
 */
public class EventLoad implements Event {
    String path;
    public EventLoad(String path) {this.path = path;}

    public String getPath() {
        return path;
    }
}
