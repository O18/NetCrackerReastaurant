package com.sanik3d.restaurant.strategy;

import com.sanik3d.restaurant.events.Event;
import com.sanik3d.restaurant.view.View;

/**
 * Created by 1 on 04.12.2016.
 */
public class Context {
    private Strategy strategy;
    private View view;

    public Context(View view) {this.view = view;}

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }
    public Event executeStrategy(String[] commandString){
        return strategy.createEvent(commandString);
    }
}
