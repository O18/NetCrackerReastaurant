package com.sanik3d.restaurant.eventbus;

import com.sanik3d.restaurant.eventbus.event.Event;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Александр on 12.11.2016.
 */
public class EventBus {
    private static EventBus eventBus;

    private Set<Listener> listeners = new HashSet<>();

    public static EventBus getInstance(){
        if(eventBus == null)
            eventBus = new EventBus();

        return eventBus;
    }

    private EventBus(){}

    public void addListener(Listener l){
        listeners.add(l);
    }

    public void deleteListener(Listener l){
        listeners.remove(l);
    }

    public void post(Event e){
        for (Listener l : listeners) {
            l.onEvent(e);
        }
    }
}