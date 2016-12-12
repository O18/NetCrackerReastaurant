package com.sanik3d.restaurant;

import com.sanik3d.restaurant.controller.MenuController;
import com.sanik3d.restaurant.eventbus.EventBus;
import com.sanik3d.restaurant.model.Menu;
import com.sanik3d.restaurant.presenter.Presenter;
import com.sanik3d.restaurant.view.Parser;
import com.sanik3d.restaurant.view.View;

/**
 * Created by Александр on 13.12.2016.
 */
class LifeCycle {

    void start() {
        View view = new View();
        EventBus eventBus = new EventBus();
        Menu menu = new Menu();
        MenuController menuController = new MenuController(menu, eventBus);
        Presenter presenter = new Presenter(eventBus, new Parser(), view, menu);
        view.setPresenter(presenter);
        view.begin();
    }
}
