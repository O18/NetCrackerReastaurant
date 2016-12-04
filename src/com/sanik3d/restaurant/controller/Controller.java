package com.sanik3d.restaurant.controller;

import com.sanik3d.restaurant.eventbus.EventBus;
import com.sanik3d.restaurant.events.*;
import com.sanik3d.restaurant.model.Category;
import com.sanik3d.restaurant.model.Dish;
import com.sanik3d.restaurant.model.Menu;

import java.io.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Александр on 12.11.2016.
 */
public class Controller {

    private Menu menu;
    private MenuCache cache;

    public Controller(Menu menu, EventBus eventBus) {
        this.menu = menu;
        cache = new MenuCache(menu);
        eventBus.addHandler(LoadInMemoryEvent.class,
                this::loadMenuFrom);
        eventBus.addHandler(SaveMenuEvent.class,
                this::saveMenuTo);
        eventBus.addHandler(AddCategoryEvent.class,
                this::addCategory);
        eventBus.addHandler(AddDishEvent.class,
                this::addDish);
        eventBus.addHandler(DeleteCategoryEvent.class,
                this::deleteCategory);
        eventBus.addHandler(DeleteDishEvent.class,
                this::deleteDish);
        eventBus.addHandler(ShowAllCategoriesEvent.class,
                this::showAllCategories);
        eventBus.addHandler(ShowAllDishesEvent.class,
                this::showAllDishes);
    }

    private void loadMenuFrom(LoadInMemoryEvent event) {
        String filePath = event.getPath();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filePath))) {
            Menu loadedMenu = (Menu) in.readObject();
            in.close();
            menu = loadedMenu;
            event.getCallback().onSuccess();
        } catch (FileNotFoundException e) {
            event.getCallback().onFail(new RuntimeException("Файл не существует", e));

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
            event.getCallback().onFail(new RuntimeException("Ошибка записи", e));
        }
    }

    private void addCategory(AddCategoryEvent event) {
        String categoryName = event.getNameOfCategory();

        if(!cache.namesAndCategories.containsKey(categoryName)){
            Category categoryToAdd = new Category(categoryName);
            menu.addCategory(categoryToAdd);
            cache.namesAndCategories.put(categoryName, categoryToAdd);

            event.getCallback().onSuccess();
        }
        else {
            event.getCallback().onFail(new RuntimeException("Категория с таким именем уже существует"));
        }

    }

    private void deleteCategory(DeleteCategoryEvent event) {
        String categoryName = event.getNameOfCategory();

        if(cache.namesAndCategories.containsKey(categoryName)){
            Category category = cache.namesAndCategories.get(categoryName);
            menu.deleteCategory(category);
            cache.deleteCategory(category);
            event.getCallback().onSuccess();
        }
        else {
            event.getCallback().onFail(new RuntimeException("Категории с таким именем не существует"));
        }
    }

    private void addDish(AddDishEvent event) {
        String categoryName = event.getCategory();

        if(cache.namesAndDishes.containsKey(categoryName)){
            String dishName = event.getNameOfDish();

            if(!cache.namesAndDishes.containsKey(dishName)){
                Dish dishToAdd = createDishFromEvent(event);
                menu.addDish(dishToAdd);
                cache.addDish(dishToAdd);
                event.getCallback().onSuccess();
            }
            else {
                event.getCallback().onFail(new RuntimeException(
                        "Ошибка добавления блюда. Блюдо с таким именем уже существует"));
            }
        }
        else{
            event.getCallback().onFail(new RuntimeException(
                    "Ошибка добавления блюда. Категории с таким именем не существует"));
        }
    }

    private Dish createDishFromEvent(AddDishEvent event) {
        String dishName = event.getNameOfDish();
        Category category = cache.namesAndCategories.get(event.getCategory());
        double price = event.getPriceOfDish();

        return new Dish(dishName, category, price);
    }

    private void deleteDish(DeleteDishEvent event){
        String dishName = event.getNameOfDish();

        if(cache.namesAndDishes.containsKey(dishName)){
            Dish dishToDelete = cache.namesAndDishes.get(dishName);
            menu.deleteDish(dishToDelete);
            cache.deleteDish(dishToDelete);

            event.getCallback().onSuccess();
        }
        else{
            event.getCallback().onFail(new RuntimeException(
                    "Ошибка удаления блюда. Блюдо с таким именем не уществует"));
        }
    }

    private void showAllDishes(ShowAllDishesEvent event) {
        Set<Dish> dishes = menu.getDishes();
        event.getCallback().onSuccess(Collections.unmodifiableSet(dishes));
    }

    private void showAllCategories(ShowAllCategoriesEvent event) {
        Set<Category> categories = menu.getCategories();
        event.getCallback().onSuccess(Collections.unmodifiableSet(categories));
    }

    /**
     * Created by Александр on 29.11.2016.
     */
    static class MenuCache {
        private final Menu menu;
        private final Map<String, Category> namesAndCategories;
        private final Map<String, Dish> namesAndDishes;

        MenuCache(Menu menu) {
            this.menu = menu;
            namesAndCategories = new HashMap<>();
            namesAndDishes = new HashMap<>();
            addCategories(menu.getCategories());
        }

        void addCategories(Set<Category> categories){
            for (Category category : categories) {
                addCategory(category);
                for (Dish dish: category.getDishes()) {
                    addDish(dish);
                }
            }
        }

        void addCategory(Category category){
            namesAndCategories.put(category.getName(), category);
        }

        void deleteCategory(Category category){
            namesAndCategories.remove(category.getName(), category);
        }

        void addDish(Dish dish){
            namesAndDishes.put(dish.getName(), dish);
        }

        void deleteDish(Dish dish){
            namesAndDishes.remove(dish.getName());
        }
    }
}