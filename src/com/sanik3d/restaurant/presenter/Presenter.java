package com.sanik3d.restaurant.presenter;

import com.sanik3d.restaurant.eventbus.EventBus;
import com.sanik3d.restaurant.events.*;
import com.sanik3d.restaurant.exceptions.NotEnoughDataException;
import com.sanik3d.restaurant.exceptions.NotEnoughtDataException;
import com.sanik3d.restaurant.presenter.callbacks.*;
import com.sanik3d.restaurant.view.Parser;
import com.sanik3d.restaurant.view.View;

import java.util.HashMap;


/**
 * Created by 1 on 13.11.2016.
 */
public class Presenter {
    private String[] strings;
    private final EventBus eventBus;
    private final Parser parser;
    private final View view;//TODO: View должна реализовывать интерфейс

    public Presenter(EventBus eventBus, Parser parser, View view) {
        this.eventBus = eventBus;
        this.parser = parser;
        this.view = view;
    }

    public void sendEvent(String inString) throws NotEnoughtDataException {
        try {//TODO: вынести лишний код из try
            String command = parser.getCommand(inString);
            String message = "Недостаточно даннных для выполнения команды! Пожалуйста, повторите ввод.";
            HashMap<String, Event> map = new HashMap<>();
            String[] strings = parser.getArgs(inString);
            if (command.equals("add_dish")) {
                if (strings.length < 3)
                    throw new NotEnoughDataException(message);
                map.put("add_dish", new AddDishEvent(strings[0], Double.valueOf(strings[1]), strings[2], new AddDishCallback() {
                    @Override
                    public void onSuccess() {
                        view.print("Добавление блюда прошло успешно!");
                    }

                    @Override
                    public void onFailDishAlreadyExists() {
                        view.print("Неудача! Такое блюдо уже создано.");
                    }

                    @Override
                    public void onFailCategoryDoesNotExist() {
                        view.print("Неудача! Указанной категории не существует.");//TODO:предложить создать такую категорию
                    }
                }));
            }
            else {
                if (strings.length < 1)
                    throw new NotEnoughDataException(message);
                map.put("add_category", new AddCategoryEvent(strings[0], new AddCategoryCallback() {
                    @Override
                    public void onSuccess() {
                        view.print("Добавление категории прошло успешно!");
                    }

                    @Override
                    public void onFailCategoryAlreadyExists() {
                        view.print("Неудача! Такая категория уже создана.");
                    }
                }));
                map.put("delete_dish", new DeleteDishEvent(strings[0], new DeleteDishCallback() {
                    @Override
                    public void onSuccess() {
                        view.print("Удаление блюда прошло успешно!");
                    }

                    @Override
                    public void onFailDishDoesNotExist() {
                        view.print("Неудача! Указанное блюдо не существует.");
                    }
                }));
                map.put("delete_category", new DeleteCategoryEvent(strings[0], new DeleteCategoryCallback() {
                    @Override
                    public void onSuccess() {
                        view.print("Удаление категории прошло успешно!");
                    }

                    @Override
                    public void onFailCategoryDoesNotExist() {
                        view.print("Неудача! Указанная категория не существует.");
                    }

                    @Override
                    public void onFailRemoveDishesOfCategory() {
                        view.print("Внимание! При удалении категории, удалятся все связанные с ней блюда!");
                    }
                }));
                map.put("load", new LoadInMemoryEvent(strings[0], new LoadInMemoryCallback() {
                    @Override
                    public void onSuccess() {
                        view.print("Загрузка меню в память прошла успешно!");
                    }

                    @Override
                    public void onFailFileNotFound() {
                        view.print("Неудача! Файл не найден.");
                    }

                    @Override
                    public void onFailReadError() {
                        view.print("Неудача! Ошибка чтения.");

                    }
                }));
                map.put("save", new SaveMenuEvent(strings[0], new SaveMenuCallback() {
                    @Override
                    public void onSuccess() {

                        view.print("Сохранение меню прошло успешно!");
                    }

                    @Override
                    public void onFailWriteError() {

                        view.print("Неудача! Ошибка записи");
                    }
                }));
                map.put("show_all_dishes", new ShowAllDishesEvent(new ShowAllDishesCallback() {
                    @Override
                    public void onSuccess() {
                        view.print("Полный список блюд :");
                    }//TODO: сделать возвращаемое значение

                    @Override
                    public void onFail() {
                        view.print("Неудача! Произошла ошибка при выводе списка.");
                    }
                }));
                map.put("show_all_categories", new ShowAllCategoriesEvent(new ShowAllCategoriesCallback() {
                    @Override
                    public void onSuccess() {
                        view.print("Полный список категорий: ");
                    }//TODO: сделать возвращаемое значение

                    @Override
                    public void onFail() {
                        view.print("Неудача! Произошла ошибка при выводе списка.");
                    }
                }));
            }//TODO: переделать switch с помощью паттерна Стратегия
            eventBus.post(map.get(command));//fire
            //здесь выдает ошибку из-за
            // скобки - преобразования типа, а если ее убрать не собирается проект из-за другой ошибки !
        } catch (NotEnoughDataException e) {
            view.print(e.getMessage());
        }//TODO:разобраться с исключениями
        /* strings = parser.getArrayOfStrings(inString);
        Object[] strAndDig = new Object[inString.length()];
        int j = 0;
        for (String str : strings) {
            boolean flag = true;
            for (int i = 0; i < str.length() && flag; i++)
                if (!Character.isDigit(str.charAt(i)))
                    flag = false;
            if (flag)
                strAndDig[j] = Double.valueOf(str);
            else
                strAndDig[j] = str;
        }
        j = 0;

        switch (String.valueOf(strAndDig[j++])) {
            case "load": {
                if (strAndDig.length < (j + 1))
                    throw new ExceptionPath();
                Event event = new LoadInMemoryEvent(String.valueOf(strAndDig[j]));
                eventBus.post(event);
            }
            break;
            case "save": {
                if (strAndDig.length < (j + 1))
                    throw new ExceptionPath();
                Event event = new SaveMenuEvent(String.valueOf(strAndDig[j]));
                eventBus.post(event);
            }
            break;
            case "add dish": {
                if (strAndDig.length < (j + 3))
                    throw new NotEnoughtDataException(message);
                Event event = new AddDishEvent(String.valueOf(strAndDig[j++]), Double.valueOf(String.valueOf(strAndDig[j++])), String.valueOf(strAndDig[j]));
                eventBus.post(event);
            }
            break;
            case "add category": {
                if (strAndDig.length < (j + 1))
                    throw new ExceptionNameCategory();
                Event event = new AddCategoryEvent(String.valueOf(strAndDig[j]));
                eventBus.post(event);
            }
            case "delete dish": {
                if (strAndDig.length < (j + 3))
                    throw new ExceptionNameDish();
                Event event = new DeleteDishEvent(String.valueOf(strAndDig[j]));
                eventBus.post(event);
            }
            break;
            case "delete category": {
                if (strAndDig.length < (j + 3))
                    throw new ExceptionNameCategory();
                //ПРЕДУПРЕДИТЬ, ЧТО УДАЛЯТСЯ ВСЕ БЛЮДА ДАННОЙ КАТЕГОРИИ
                Event event = new DeleteCategoryEvent(String.valueOf(strAndDig[j]));
                eventBus.post(event);
            }
        }*/
    }
}
