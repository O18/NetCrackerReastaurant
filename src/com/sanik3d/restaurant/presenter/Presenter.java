package com.sanik3d.restaurant.presenter;

import com.sanik3d.restaurant.eventbus.EventBus;
import com.sanik3d.restaurant.exceptions.NotEnoughDataException;
import com.sanik3d.restaurant.model.Menu;
import com.sanik3d.restaurant.view.Parser;
import com.sanik3d.restaurant.view.View;

import java.util.HashMap;


public class Presenter {
    private static final String ADD_CATEGORY = "add_cat";
    private static final String ADD_DISH = "add_dish";
    private static final String DELETE_DISH = "del_dish";
    private static final String DELETE_CATEGORY = "del_cat";
    private static final String LOAD_MENU = "load";
    private static final String SAVE_MENU = "save";
    private static final String HELP = "help";
    private static final String SHOW_DISHES = "show_d";
    private static final String SHOW_CATEGORIES = "show_c";

    private final EventBus eventBus;
    private final Parser parser;
    private final View view;
    private Menu menu;
    private HashMap<String, PresenterStrategy> mapNameCreator;

    public Presenter(EventBus eventBus, Parser parser, View view, Menu menu) {
        this.eventBus = eventBus;
        this.parser = parser;
        this.view = view;
        view.setPresenter(this);
        this.menu = menu;

        mapNameCreator = new HashMap<>();
        mapNameCreator.put(ADD_CATEGORY, new AddCategoryStrategy(this));
        mapNameCreator.put(ADD_DISH, new AddDishStrategy(this));
        mapNameCreator.put(DELETE_DISH, new DeleteDishStrategy(this));
        mapNameCreator.put(LOAD_MENU, new LoadMenuStrategy(this));
        mapNameCreator.put(DELETE_CATEGORY, new DeleteCategoryStrategy(this));
        mapNameCreator.put(SAVE_MENU, new SaveMenuStrategy(this));
        mapNameCreator.put(HELP, new ShowHelpStrategy(this));
        mapNameCreator.put(SHOW_DISHES, new ShowDishesStrategy(this));
        mapNameCreator.put(SHOW_CATEGORIES, new ShowCategoriesStrategy(this));
    }

    EventBus getEventBus() {
        return eventBus;
    }

    View getView() {
        return view;
    }

    Menu getMenu() {
        return menu;
    }

    public void decodeCommand(String inString) {
        String command = parser.getCommand(inString);
        String[] args = parser.getArgs(inString);
        PresenterStrategy action = mapNameCreator.get(command);
        if (action == null) {
            view.print("Неверная комманда. Повторите ввод.");
            return;
        }
        try {
            action.performAction(args);
        } catch (NotEnoughDataException e) {
            view.print(e.getMessage());
        }
    }
}
