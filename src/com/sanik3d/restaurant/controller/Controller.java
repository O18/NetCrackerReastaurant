package com.sanik3d.restaurant.controller;

import com.sanik3d.restaurant.eventbus.EventBus;
import com.sanik3d.restaurant.eventbus.Listener;
import com.sanik3d.restaurant.eventbus.event.*;
import com.sanik3d.restaurant.model.Category;
import com.sanik3d.restaurant.model.Dish;
import com.sanik3d.restaurant.model.Menu;

import javax.xml.ws.handler.Handler;
import java.io.*;
import java.util.HashMap;

/**
 * Created by Александр on 12.11.2016.
 */
public class Controller implements Listener {

    private Menu menu;
    //TODO: EventBus
    EventBus eventBus;

    public Controller(Menu menu, EventBus eventBus) {
        this.menu = menu;
        this.eventBus = eventBus;
        this.eventBus.addListener(EventLoad.class, new Handler<EventLoad>{
            void handle(EventLoad event)
            {
                loadMenuFrom(((EventLoad) e).getPath());
            }
        });
    }

    @Override
    public void onEvent(Event e) {//TODO: проверка на существование зависимостей
        if(e instanceof EventLoad)

        else if(e instanceof EventSave)
            saveMenuTo(((EventSave) e).getPath());
        else if(e instanceof EventAddCategory)
            setCategory(((EventAddCategory) e).getNameOfCategory());
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

    private void setCategory(String name) {
        menu.addCategory(new Category(name));
    }

    private void saveMenuTo(String filePath) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filePath))) {
            out.writeObject(menu);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            System.err.println("Сохранение не удалось");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadMenuFrom(String filePath) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filePath))) {
            Menu loadedMenu = (Menu) in.readObject();
            in.close();
            menu = loadedMenu;
        } catch (FileNotFoundException e) {
            System.err.println("Файл не найден!");
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }
}
