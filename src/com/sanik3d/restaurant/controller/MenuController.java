package com.sanik3d.restaurant.controller;

import com.sanik3d.restaurant.eventbus.EventBus;
import com.sanik3d.restaurant.events.*;
import com.sanik3d.restaurant.model.Category;
import com.sanik3d.restaurant.model.Dish;
import com.sanik3d.restaurant.model.Menu;
import com.sanik3d.restaurant.model.MenuListener;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Александр on 12.11.2016.
 */
public class MenuController {
    private final Menu menu;
    private final MenuCache cache;

    public MenuController(Menu menu, EventBus eventBus) {
        this.menu = menu;
        cache = new MenuCache(menu);
        eventBus.addHandler(LoadMenuInMemoryEvent.class, this::loadMenuFrom);
        eventBus.addHandler(SaveMenuEvent.class, this::saveMenuTo);
        eventBus.addHandler(AddCategoryEvent.class, this::addCategory);
        eventBus.addHandler(AddDishEvent.class, this::addDish);
        eventBus.addHandler(DeleteCategoryEvent.class, this::deleteCategory);
        eventBus.addHandler(DeleteDishEvent.class, this::deleteDish);
    }

    private void loadMenuFrom(LoadMenuInMemoryEvent event) {
        String filePath = event.getPath();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filePath))) {
            Menu loadedMenu = (Menu) in.readObject();
            in.close();
            Set<Category> categories = new HashSet<>();
            for (Category category : loadedMenu.getCategories()) {
                categories.add(category);
            }
            menu.setCategories(categories);
            cache.changeMenu(menu);

            event.getCallback().onSuccess();
        } catch (FileNotFoundException e) {
            event.getCallback().onFail(new RuntimeException("Файл не существует", e));

        } catch (ClassNotFoundException | IOException e) {
            event.getCallback().onFail(new RuntimeException("Ошибка чтения файла", e));
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

        if (!cache.namesAndCategories.containsKey(categoryName)) {
            Category categoryToAdd = new Category(categoryName);
            menu.addCategory(categoryToAdd);

            event.getCallback().onSuccess();
        } else {
            event.getCallback().onFail(new RuntimeException("Категория с таким именем уже существует"));
        }
    }

    private void deleteCategory(DeleteCategoryEvent event) {
        String categoryName = event.getNameOfCategory();

        if (cache.namesAndCategories.containsKey(categoryName)) {
            Category category = cache.namesAndCategories.get(categoryName);
            menu.deleteCategory(category);

            event.getCallback().onSuccess();
        } else {
            event.getCallback().onFail(new RuntimeException("Категории с таким именем не существует"));
        }
    }

    private void addDish(AddDishEvent event) {
        String categoryName = event.getCategory();

        if (cache.namesAndCategories.containsKey(categoryName)) {
            String dishName = event.getNameOfDish();

            if (!cache.namesAndDishes.containsKey(dishName)) {
                Dish dishToAdd = createDishFromEvent(event);
                menu.addDishToCategory(dishToAdd, cache.namesAndCategories.get(categoryName));

                event.getCallback().onSuccess();
            } else {
                event.getCallback().onFail(new RuntimeException(
                        "Ошибка добавления блюда. Блюдо с таким именем уже существует"));
            }
        } else {
            event.getCallback().onFail(new RuntimeException(
                    "Ошибка добавления блюда. Категории с таким именем не существует"));
        }
    }

    private Dish createDishFromEvent(AddDishEvent event) {
        String dishName = event.getNameOfDish();
        double price = event.getPriceOfDish();

        return new Dish(dishName, event.getCategory(), price);
    }

    private void deleteDish(DeleteDishEvent event) {
        String dishName = event.getNameOfDish();

        if (cache.namesAndDishes.containsKey(dishName)) {
            Dish dishToDelete = cache.namesAndDishes.get(dishName);
            Category category = cache.namesAndCategories.get(dishToDelete.getCategoryName());

            menu.deleteDishFromCategory(dishToDelete, category);

            event.getCallback().onSuccess();
        } else {
            event.getCallback().onFail(new RuntimeException(
                    "Ошибка удаления блюда. Блюда с таким именем не существует"));
        }
    }

    /**
     * Created by Александр on 29.11.2016.
     */
    private static class MenuCache implements MenuListener, Serializable {
        private static final long serialVersionUID = -4469390559401629523L;

        private final Menu menu;
        private final Map<String, Category> namesAndCategories;
        private final Map<String, Dish> namesAndDishes;

        MenuCache(Menu menu) {
            this.menu = menu;
            namesAndCategories = new HashMap<>();
            namesAndDishes = new HashMap<>();
            addCategoriesAndDishes(menu.getCategories());
            menu.addListener(this);
        }

        private void addCategoriesAndDishes(Set<Category> categories) {
            for (Category category : categories) {
                addCategory(category);
            }
        }

        @Override
        public void onAddCategory(Category category) {
            addCategory(category);
        }

        @Override
        public void onDeleteCategory(Category category) {
            deleteCategory(category);
        }

        @Override
        public void onAddDish(Dish dish) {
            addDish(dish);
        }

        @Override
        public void onDeleteDish(Dish dish) {
            deleteDish(dish);
        }

        private void addDish(Dish dish) {
            namesAndDishes.put(dish.getName(), dish);
        }

        private void deleteDish(Dish dish) {
            namesAndDishes.remove(dish.getName());
        }

        private void addCategory(Category category){
            namesAndCategories.put(category.getName(), category);
            for (Dish dish : category.getDishes()) {
                addDish(dish);
            }
        }

        private void deleteCategory(Category category){
            namesAndCategories.remove(category.getName(), category);
            for (Dish dish : category.getDishes()) {
                deleteDish(dish);
            }
        }

        private void changeMenu(Menu newMenu) {
            namesAndCategories.clear();
            namesAndDishes.clear();
            addCategoriesAndDishes(newMenu.getCategories());
        }
    }
}