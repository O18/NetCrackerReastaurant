package com.sanik3d.restaurant.presenter;

import com.sanik3d.restaurant.eventbus.EventBus;
import com.sanik3d.restaurant.events.*;
import com.sanik3d.restaurant.exceptions.NotEnoughDataException;
import com.sanik3d.restaurant.model.Category;
import com.sanik3d.restaurant.model.Dish;
import com.sanik3d.restaurant.model.Menu;
import com.sanik3d.restaurant.view.Parser;
import com.sanik3d.restaurant.view.View;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;


public class Presenter {
    private static final String ADD_CATEGORY = "add_category";
    private static final String ADD_DISH = "add_dish";
    private static final String DELETE_DISH = "delete_dish";
    private static final String DELETE_CATEGORY = "delete_category";
    private static final String LOAD_MENU = "load_menu";
    private static final String SAVE_MENU = "save_menu";
    private static final String HELP = "help";
    private static final String SHOW_DISHES = "show_dishes";
    private static final String SHOW_CATEGORIES = "show_categories";

    private final EventBus eventBus;
    private final Parser parser;
    private final View view;
    private Menu menu;
    private HashMap<String, Creator> mapNameCreator;

    public Presenter(EventBus eventBus, Parser parser, View view, Menu menu) {
        this.eventBus = eventBus;
        this.parser = parser;
        this.view = view;
        view.setPresenter(this);
        this.menu = menu;

        mapNameCreator = new HashMap<>();
        mapNameCreator.put(ADD_CATEGORY, new AddCategoryCreator(this.view));
        mapNameCreator.put(ADD_DISH, new AddDishCreator(this.view));
        mapNameCreator.put(DELETE_DISH, new DeleteDishCreator(this.view));
        mapNameCreator.put(DELETE_CATEGORY, new DeleteCategoryCreator(this.view));
        mapNameCreator.put(LOAD_MENU, new LoadMenuInMemoryCreator(this.view));
        mapNameCreator.put(SAVE_MENU, new SaveMenuCreator(this.view));
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

    public static class AddDishCreator implements Creator {
        private View view;

        public AddDishCreator(View view) {
            this.view = view;
        }

        @Override
        public Event createEvent(String[] commandString) throws NotEnoughDataException {
            if (commandString.length < 3)
                throw new NotEnoughDataException("Недостаточно данных. Введите название блюда, стоимость " +
                        "и название категории для добавлеия блюда. Категория уже должна сущестовать.");
            return new AddDishEvent(commandString[0], Double.valueOf(commandString[1]), commandString[2], new Callback() {
                @Override
                public void onSuccess() {
                    view.print("Добавление блюда прошло успешно!");
                }

                @Override
                public void onFail(RuntimeException e) {
                    view.print(e.getMessage());
                }
            });
        }
    }

    public static class AddCategoryCreator implements Creator {

        public AddCategoryCreator(View view) {
            this.view = view;
        }

        private View view;

        @Override
        public Event createEvent(String[] commandString) throws NotEnoughDataException {
            if (commandString.length < 1)
                throw new NotEnoughDataException("Недостаточно данных. Введите название категории для её добавления.");
            return new AddCategoryEvent(commandString[0], new Callback() {

                @Override
                public void onSuccess() {
                    view.print("Добавление категории прошло успешно!");
                }

                @Override
                public void onFail(RuntimeException e) {
                    view.print(e.getMessage());
                }
            });
        }
    }

    public static class DeleteCategoryCreator implements Creator {
        public DeleteCategoryCreator(View view) {
            this.view = view;
        }

        private View view;

        public Event createEvent(String[] commandString) throws NotEnoughDataException {
            if (commandString.length < 1)
                throw new NotEnoughDataException("Недостаточно данных. Введите название категории для её удаления. " +
                        "Категория должна сущестовать");
            return new DeleteCategoryEvent(commandString[0], new Callback() {

                @Override
                public void onSuccess() {
                    view.print("Удаление категории прошло успешно!");
                }

                @Override
                public void onFail(RuntimeException e) {
                    view.print(e.getMessage());
                }
            });
        }
    }


    public static class DeleteDishCreator implements Creator {
        private View view;

        public DeleteDishCreator(View view) {
            this.view = view;
        }

        public Event createEvent(String[] commandString) throws NotEnoughDataException {
            if (commandString.length < 1)
                throw new NotEnoughDataException("Недостаточно данных. Введите название блюда для его удаления. " +
                        "Блюдо должно существовать");
            return new DeleteDishEvent(commandString[0], new Callback() {

                @Override
                public void onSuccess() {
                    view.print("Удаление блюда прошло успешно!");
                }

                @Override
                public void onFail(RuntimeException e) {
                    view.print(e.getMessage());
                }
            });
        }
    }


    public static class LoadMenuInMemoryCreator implements Creator {
        private View view;

        public LoadMenuInMemoryCreator(View view) {
            this.view = view;
        }

        public Event createEvent(String[] commandString) throws NotEnoughDataException {
            if (commandString.length < 1)
                throw new NotEnoughDataException("Недостаточно данных. Введите полный путь для загрузки меню в память.");
            return new LoadMenuInMemoryEvent(commandString[0], new Callback() {

                @Override
                public void onSuccess() {
                    view.print("Загрузка меню в память прошла успешно!");
                }

                @Override
                public void onFail(RuntimeException e) {
                    view.print(e.getMessage());
                }
            });
        }
    }


    public static class SaveMenuCreator implements Creator {
        View view;

        public SaveMenuCreator(View view) {
            this.view = view;
        }

        public Event createEvent(String[] commandString) throws NotEnoughDataException {
            if (commandString.length < 1)
                throw new NotEnoughDataException("Недостаточно данных. Введите полный путь для сохранения изменений в меню.");
            return new SaveMenuEvent(commandString[0], new Callback() {

                @Override
                public void onSuccess() {
                    view.print("Соханение меню прошло успешно!");
                }

                @Override
                public void onFail(RuntimeException e) {
                    view.print(e.getMessage());
                }
            });
        }
    }

    public interface Creator {
        Event createEvent(String[] commandString) throws NotEnoughDataException;
    }

    public void decodingCommand(String inString) {
        String command = parser.getCommand(inString);
        String[] args = parser.getArgs(inString);
        switch (command) {
            case HELP:
                try {
                    BufferedReader reader = new BufferedReader(new FileReader("help.txt"));
                    StringBuilder result = new StringBuilder();
                    String currentLine;
                    while ((currentLine = reader.readLine()) != null) {
                        result.append(currentLine).append('\n');
                    }
                    view.print(result.toString());
                    reader.close();
                } catch (IOException e) {
                    view.print("Не найден файл");
                }
                break;
            case SHOW_DISHES:
                for (Dish dish : menu.getDishes())
                    view.print(dish.getName() + ' ' + dish.getCost() + ' ' + dish.getCategoryName());
                break;
            case SHOW_CATEGORIES:
                for (Category category : menu.getCategories())
                    view.print(category.getName());
                break;
            default:
                try {
                    eventBus.post(mapNameCreator.get(command).createEvent(args));
                } catch (NotEnoughDataException e) {
                    view.print(e.getMessage());
                } catch (NullPointerException e) {
                    view.print("Неверная команда");
                }
                break;
        }
    }


}
