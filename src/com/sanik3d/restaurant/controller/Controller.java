package com.sanik3d.restaurant.controller;

import com.sanik3d.restaurant.eventbus.EventBus;
import com.sanik3d.restaurant.eventbus.events.*;
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
        this.eventBus.addHandler(EventDeleteDish.class,
                event -> deleteDish((EventDeleteDish)event));
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

    private void deleteCategory(EventDeleteCategory event) {
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

    private void addDish(EventAddDish event) {
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

    private Dish createDishFromEvent(EventAddDish event) {
        String dishName = event.getNameOfDish();
        Category category = menu.getNameCategoryMap().get(event.getCategory());
        double price = event.getPriceOfDish();

        return new Dish(dishName, category, price);
    }

    private void deleteDish(EventDeleteDish event){
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