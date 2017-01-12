package com.o18.restaurant.presenter;

import com.o18.restaurant.eventbus.EventBus;
import com.o18.restaurant.exceptions.NotEnoughDataException;
import com.o18.restaurant.model.Menu;
import com.o18.restaurant.view.Parser;
import com.o18.restaurant.view.View;

import java.util.HashMap;


public class Presenter {//TODO
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

    EventBus getEventBus() {//TODO remove
        return eventBus;
    }

    View getView() {
        return view;
    }//TODO remove

    Menu getMenu() {
        return menu;
    }//TODO remove

    public void decodeCommand(String inString) {
        String command = parser.getCommand(inString);//TODO
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
