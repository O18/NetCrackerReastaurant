package com.sanik3d.restaurant.eventbus;

import com.sanik3d.restaurant.events.Event;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Александр on 12.11.2016.
 */
public class EventBus {

    private Map<Class, Handler> handlers = new HashMap<>();//TODO: collections Handler and generics

    public EventBus() {
    }

    public <T extends Event> void addHandler(Class<T> handlerClass, Handler<T> handler) {
        handlers.put(handlerClass, handler);
    }

    public void deleteHandler(Class handlerClass) {//TODO: Handler
        handlers.remove(handlerClass);
    }

    /*public void post(Event e) {
        for (Class handlerClass : handlers.keySet()) {
            if (handlerClass == e.getClass()) {
                handlers.get(handlerClass).handle(e);
            }
        }
    }*/

    public void post(Event event) {//TODO: handler по EventClass
        for (Class handlerClass : handlers.keySet()) {
            if (handlerClass == event.getClass()) {
                handlers.get(handlerClass).handle(event);
            }
        }
    }
}