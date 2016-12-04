package com.sanik3d.restaurant.presenter;

import com.sanik3d.restaurant.eventbus.EventBus;
import com.sanik3d.restaurant.events.*;
import com.sanik3d.restaurant.exceptions.NotEnoughDataException;
import com.sanik3d.restaurant.exceptions.NotEnoughtDataException;
import com.sanik3d.restaurant.presenter.callbacks.*;
import com.sanik3d.restaurant.strategy.*;
import com.sanik3d.restaurant.view.Parser;
import com.sanik3d.restaurant.view.View;


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

    public void decodingCommand(String inString) throws NotEnoughtDataException {
        Context context = new Context(this.view);
        String command = parser.getCommand(inString);
        String message = "Недостаточно даннных для выполнения команды! Пожалуйста, повторите ввод.";
        String[] strings = parser.getArgs(inString);
        try {//TODO: вынести лишний код из try
            if (command.equals("add_dish")) {
                if (strings.length < 3)
                    throw new NotEnoughDataException(message);
                context.setStrategy(new AddDishStrategy(this.view));
            } else {
                if (strings.length < 1)
                    throw new NotEnoughDataException(message);
                switch (command) {
                    case "add_category":
                        context.setStrategy(new AddCategoryStrategy(this.view));
                        break;
                    case "delete_dish":
                        context.setStrategy(new DeleteDishStrategy(this.view));
                        break;
                    case "delete_category":
                        context.setStrategy(new DeleteCategoryStrategy(this.view));
                        break;
                    case "load_menu_in_memory":
                        context.setStrategy(new LoadMenuInMemoryStrategy(this.view));
                        break;
                    case "save_menu":
                        context.setStrategy(new SaveMenuStrategy(this.view));
                        break;
                    case "show_all_categories":
                        context.setStrategy(new ShowAllCategoriesStrategy(this.view));
                    case "show_all_dishes":
                        context.setStrategy(new ShowAllDishesStrategy(this.view));
                }
            }//TODO: переделать switch с помощью паттерна Стратегия*/
        }
        catch(NotEnoughDataException e){
            view.print(e.getMessage());
        }//TODO:разобраться с исключениями
        eventBus.post(context.executeStrategy(strings));//fire
    }
}
