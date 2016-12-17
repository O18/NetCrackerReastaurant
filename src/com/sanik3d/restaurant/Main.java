package com.sanik3d.restaurant;

import com.sanik3d.restaurant.controller.MenuController;
import com.sanik3d.restaurant.eventbus.EventBus;
import com.sanik3d.restaurant.model.Menu;
import com.sanik3d.restaurant.presenter.Presenter;
import com.sanik3d.restaurant.view.Parser;
import com.sanik3d.restaurant.view.View;

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
