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
//TODO: Maven сборка
    public static void main(String[] args) {
        View view = new View();
        EventBus eventBus = new EventBus();
        MenuController menuController = new MenuController(new Menu(), eventBus);
        Presenter presenter = new Presenter(eventBus, new Parser(), view);
        view.setPresenter(presenter);
        view.begin();
    }
}
