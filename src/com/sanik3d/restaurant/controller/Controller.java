package com.sanik3d.restaurant.controller;

import com.sanik3d.restaurant.eventbus.EventBus;
import com.sanik3d.restaurant.events.*;
import com.sanik3d.restaurant.model.Category;
import com.sanik3d.restaurant.model.Dish;
import com.sanik3d.restaurant.model.Menu;

import java.io.*;
import java.util.Map;
import java.util.Set;

/**
 * Created by Александр on 12.11.2016.
 */
public class Controller {

    private Menu menu;
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
        this.eventBus.addHandler(ShowAllCategoriesEvent.class,
                event -> showAllCategories((ShowAllCategoriesEvent)event));
        this.eventBus.addHandler(ShowAllDishesEvent.class,
                event -> showAllDishes((ShowAllDishesEvent)event));
    }

    private void loadMenuFrom(LoadInMemoryEvent event) {
        String filePath = event.getPath();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filePath))) {
            Menu loadedMenu = (Menu) in.readObject();
            in.close();
            menu = loadedMenu;
            event.getCallback().onSuccess();
        } catch (FileNotFoundException e) {
            event.getCallback().onFailFileNotFound();
        } catch (ClassNotFoundException | IOException e) {
            event.getCallback().onFailReadError();
        }
    }

    private void saveMenuTo(SaveMenuEvent event) {
        String filePath = event.getPath();
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filePath))) {
            out.writeObject(menu);
            out.flush();
            out.close();
            event.getCallback().onSuccess();
        } catch (IOException e) {
            event.getCallback().onFailWriteError();
        }
    }

    private void addCategory(AddCategoryEvent event) {
        String categoryName = event.getNameOfCategory();
        Map<String, Category> nameCategoryMap = menu.getNameCategoryMap();
        if(!nameCategoryMap.containsKey(categoryName)){
            Category categoryToAdd = new Category(categoryName);
            menu.addCategory(categoryToAdd);
            event.getCallback().onSuccess();
        }
        else {
            event.getCallback().onFailCategoryAlreadyExists();
        }

    }

    private void deleteCategory(DeleteCategoryEvent event) {
        Map<String, Category> nameCategoryMap = menu.getNameCategoryMap();
        String categoryName = event.getNameOfCategory();

        if(nameCategoryMap.containsKey(categoryName)){
            Category category = nameCategoryMap.get(categoryName);
            menu.deleteCategory(category);
            event.getCallback().onSuccess();
        }
        else {
            event.getCallback().onFailCategoryDoesNotExist();
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
                event.getCallback().onSuccess();
            }
            else {
                event.getCallback().onFailDishAlreadyExists();
            }
        }
        else{
            event.getCallback().onFailCategoryDoesNotExist();
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
            event.getCallback().onSuccess();
        }
        else{
            event.getCallback().onFailDishDoesNotExist();
        }
    }


    private void showAllDishes(ShowAllDishesEvent event) {
        Set<Dish> dishes = menu.getDishes();
        event.getCallback().onSuccess();//TODO: вернуть блюда
    }

    private void showAllCategories(ShowAllCategoriesEvent event) {
        Set<Category> categories = menu.getCategories();
        event.getCallback().onSuccess();//TODO: вернуть категории блюд
    }
}