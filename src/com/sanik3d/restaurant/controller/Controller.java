package com.sanik3d.restaurant.controller;

import com.sanik3d.restaurant.eventbus.EventBus;
import com.sanik3d.restaurant.events.*;
import com.sanik3d.restaurant.model.Category;
import com.sanik3d.restaurant.model.Dish;
import com.sanik3d.restaurant.model.Menu;

import java.io.*;
import java.util.Map;

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
        this.eventBus.addHandler(LoadInMemoryEvent.class,
                event -> loadMenuFrom(((LoadInMemoryEvent)event)));
        this.eventBus.addHandler(SaveMenuEvent.class,
                event -> saveMenuTo(((SaveMenuEvent) event)));
        this.eventBus.addHandler(AddCategoryEvent.class,
                event -> addCategory(((AddCategoryEvent) event)));
        this.eventBus.addHandler(AddDishEvent.class,
                event -> addDish((AddDishEvent) event));
        this.eventBus.addHandler(DeleteCategoryEvent.class,
                event -> deleteCategory((DeleteCategoryEvent)event));
        this.eventBus.addHandler(DeleteDishEvent.class,
                event -> deleteDish((DeleteDishEvent)event));
    }

    private void loadMenuFrom(LoadInMemoryEvent event) {
        String filePath = event.getPath();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filePath))) {
            Menu loadedMenu = (Menu) in.readObject();
            in.close();
            menu = loadedMenu;
        } catch (FileNotFoundException e) {
            event.
        } catch (ClassNotFoundException | IOException e) {
            //TODO: Callback - ошибка чтения
        }
    }

    private void saveMenuTo(SaveMenuEvent event) {
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

    private void addCategory(AddCategoryEvent event) {
        String categoryName = event.getNameOfCategory();
        Map<String, Category> nameCategoryMap = menu.getNameCategoryMap();
        if(!nameCategoryMap.containsKey(categoryName)){
            Category categoryToAdd = new Category(categoryName);
            menu.addCategory(categoryToAdd);
            //TODO: callback onSuccess
        }
        else {
            //TODO: callback категория с таким именем уже существует
        }

    }

    private void deleteCategory(DeleteCategoryEvent event) {
        Map<String, Category> nameCategoryMap = menu.getNameCategoryMap();
        String categoryName = event.getNameOfCategory();

        if(nameCategoryMap.containsKey(categoryName)){
            Category category = nameCategoryMap.get(categoryName);
            menu.deleteCategory(category);
            //TODO: callback успешно
        }
        else {
            //TODO: callback категории с таким именем не существует
        }
    }

    private void addDish(AddDishEvent event) {
        Map<String, Category> nameCategoryMap = menu.getNameCategoryMap();
        String categoryName = event.getCategory();

        if(nameCategoryMap.containsKey(categoryName)){
            Map<String, Dish> nameDishMap = menu.getNameDishMap();
            String dishName = event.getNameOfDish();

            if(!nameDishMap.containsKey(dishName)){
                Dish dishToAdd = createDishFromEvent(event);
                menu.addDish(dishToAdd);
                //TODO: callback успешно
            }
            else {
                //TODO: callback уже есть блюдо с таким именем
            }
        }
        else{
            //TODO: callback категории с таким именем не существует
        }
    }

    private Dish createDishFromEvent(AddDishEvent event) {
        String dishName = event.getNameOfDish();
        Category category = menu.getNameCategoryMap().get(event.getCategory());
        double price = event.getPriceOfDish();

        return new Dish(dishName, category, price);
    }

    private void deleteDish(DeleteDishEvent event){
        Map<String, Dish> nameDishMap = menu.getNameDishMap();
        String dishName = event.getNameOfDish();

        if(nameDishMap.containsKey(dishName)){
            Dish dishToDelete = nameDishMap.get(dishName);
            menu.deleteDish(dishToDelete);
            //TODO: callback успешно
        }
        else{
            //TODO: callback блюда с таким именем не существует
        }
    }
}