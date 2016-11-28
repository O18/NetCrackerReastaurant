package com.sanik3d.restaurant.eventbus;

import com.sanik3d.restaurant.eventbus.events.Event;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Александр on 12.11.2016.
 */
public class EventBus {
    private static EventBus eventBus;

    private Map<Class, Handler> handlers = new HashMap<>();

    public static EventBus getInstance(){
        if(eventBus == null)
            eventBus = new EventBus();

        return eventBus;
    }

    private EventBus(){}

    public void addHandler(Class handlerClass, Handler handler ){
        handlers.put(handlerClass, handler);
    }

    public void deleteHandler(Class handlerClass){
        handlers.remove(handlerClass);
    }

    public void post(Event e){
        for (Class handlerClass: handlers.keySet()){
            if(handlerClass == e.getClass()) {
                handlers.get(handlerClass).handle(e);
            }
        }
    }
}