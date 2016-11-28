package com.sanik3d.restaurant.controller;

import com.sanik3d.restaurant.eventbus.EventBus;
import com.sanik3d.restaurant.eventbus.events.*;
import com.sanik3d.restaurant.model.Category;
import com.sanik3d.restaurant.model.Dish;
import com.sanik3d.restaurant.model.Menu;

import java.io.*;
import java.util.HashMap;

/**
 * Created by Александр on 12.11.2016.
 */
public class Controller {

    private Menu menu;
    //TODO: EventBus
    private EventBus eventBus;

    public Controller(Menu menu, EventBus eventBus) {
        this.menu = menu;
        this.eventBus = eventBus;
        this.eventBus.addHandler(EventLoad.class,
                event -> loadMenuFrom(((EventLoad)event)));
        this.eventBus.addHandler(EventSave.class,
                event -> saveMenuTo(((EventSave) event)));
        this.eventBus.addHandler(EventAddCategory.class,
                event -> addCategory(((EventAddCategory) event)));
        this.eventBus.addHandler(EventAddDish.class,
                event -> addDish((EventAddDish) event));
        this.eventBus.addHandler(EventDeleteCategory.class,
                event -> deleteCategory((EventDeleteCategory)event));
    }

    private void loadMenuFrom(EventLoad event) {
        String filePath = event.getPath();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filePath))) {
            Menu loadedMenu = (Menu) in.readObject();
            in.close();
            menu = loadedMenu;
        } catch (FileNotFoundException e) {
            //TODO: Callback - не найден файд;
        } catch (ClassNotFoundException | IOException e) {
            //TODO: Callback - ошибка чтения
        }
    }

    private void saveMenuTo(EventSave event) {
        String filePath = event.getPath();
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filePath))) {
            out.writeObject(menu);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            //TODO: Callback - ошибка создания файла
        } catch (IOException e) {
            //TODO: Callback - ошибка записи
        }
    }

    private void addCategory(EventAddCategory event) {
        String categoryName = event.getNameOfCategory();
        menu.
        menu.addCategory();
    }

    private void deleteCategory(EventDeleteCategory event) {

    }

    private void addDish(EventAddDish e) {

    }

    public void onEvent(Event e) {//TODO: проверка на существование зависимостей

        else if(e instanceof EventAddDish) {
            HashMap<String, Category> map = new HashMap<>();
            for (Category c: menu.getCategories()){
                map.put(c.getName(), c);
            }//TODO: вынести в модель

            EventAddDish dishEvent = (EventAddDish)e;
            setDish(dishEvent.getNameOfDish(), map.get(dishEvent.getCategory()), dishEvent.getPriceOfDish());
        }
        //TODO: создать Callback
    }

    private void setDish(String name, Category category, double cost) {
        menu.addDish(new Dish(name, category, cost));
    }


}
