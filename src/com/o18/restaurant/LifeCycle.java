package com.o18.restaurant;

import com.o18.restaurant.controller.MenuController;
import com.o18.restaurant.eventbus.EventBus;
import com.o18.restaurant.model.Menu;
import com.o18.restaurant.presenter.Presenter;
import com.o18.restaurant.view.View;

import java.io.UnsupportedEncodingException;

/**
 * Created by Александр on 13.12.2016.
 */
class LifeCycle {
    private final View view;
    private final Menu menu;
    private final MenuController menuController;
    private final Presenter presenter;
    private final EventBus eventBus;

    LifeCycle(View view, Menu menu, MenuController menuController, Presenter presenter, EventBus eventBus) {
        this.view = view;
        this.menu = menu;
        this.menuController = menuController;
        this.presenter = presenter;
        this.eventBus = eventBus;
    }

    void start() {

        try {
            view.begin();
        } catch (UnsupportedEncodingException e) {
            System.out.println("Error. This encoding is not supported");//TODO e?
        }
    }
}
