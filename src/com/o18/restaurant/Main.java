package com.o18.restaurant;

import com.o18.restaurant.controller.MenuController;
import com.o18.restaurant.eventbus.EventBus;
import com.o18.restaurant.model.Menu;
import com.o18.restaurant.presenter.Presenter;
import com.o18.restaurant.view.Parser;
import com.o18.restaurant.view.View;

/**
 * Created by Александр on 06.11.2016.
 */
public class Main {

    public static void main(String[] args) {
        View view = new View();
        Menu menu = new Menu();
        EventBus eventBus = new EventBus();
        MenuController menuController = new MenuController(menu, eventBus);
        Presenter presenter = new Presenter(eventBus, new Parser(), view, menu);
        LifeCycle lifeCycle = new LifeCycle(view, menu, menuController, presenter, eventBus);
        lifeCycle.start();
    }
}
