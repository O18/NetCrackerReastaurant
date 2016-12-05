package com.sanik3d.restaurant.eventbus;

import com.sanik3d.restaurant.events.Event;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Александр on 12.11.2016.
 */
public class EventBus {

    private Map<Class, Set<Handler>> handlers;

    public EventBus() {
        handlers = new HashMap<>();
    }

    public <T extends Event> void addHandler(Class<T> handlerClass, Handler<T> handler) {
        Set<Handler> chosenByClassHandlers = handlers.get(handlerClass);
        if(chosenByClassHandlers == null){
            chosenByClassHandlers = new HashSet<>();
            handlers.put(handlerClass, chosenByClassHandlers);
        }
        chosenByClassHandlers.add(handler);
    }

    public void deleteHandler(Class handlerClass, Handler<Event> handlerToDelete) {
        Set<Handler> destinationHandlers = handlers.get(handlerClass);
        if(destinationHandlers != null){
            destinationHandlers.remove(handlerToDelete);
        }
    }

    public <T extends Event> void post(T event) { //TODO: handler по EventClass
        Set<Handler> handlersToPostOn = handlers.get(event.getClass());
        if(handlersToPostOn != null){
            for (Handler handler : handlersToPostOn) {
                handler.handle(event);
            }
        }
    }
}