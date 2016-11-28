package com.sanik3d.restaurant.presenter;

import com.sanik3d.restaurant.eventbus.EventBus;
import com.sanik3d.restaurant.eventbus.events.*;
import com.sanik3d.restaurant.exceptions.ExceptionAddDish;
import com.sanik3d.restaurant.exceptions.ExceptionNameCategory;
import com.sanik3d.restaurant.exceptions.ExceptionNameDish;
import com.sanik3d.restaurant.exceptions.ExceptionPath;
import com.sanik3d.restaurant.view.Parser;


/**
 * Created by 1 on 13.11.2016.
 */
public class Presenter {
    private String[] strings;
    private EventBus eventBus;
    private Parser parser;

    public void Present(String string) throws ExceptionPath, ExceptionAddDish, ExceptionNameCategory, ExceptionNameDish {
        strings = parser.Parse(string);
        Object[] strAndDig = new Object[string.length()];
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
        //TODO: переделать switch с помощью паттерна Стратегия
        switch (String.valueOf(strAndDig[j++])) {
            case "load": {
                if (strAndDig.length < (j + 1))
                    throw new ExceptionPath();
                Event event = new EventLoad(String.valueOf(strAndDig[j]));
                eventBus.post(event);
            }
            break;
            case "save": {
                if (strAndDig.length < (j + 1))
                    throw new ExceptionPath();
                Event event = new EventSave(String.valueOf(strAndDig[j]));
                eventBus.post(event);
            }
            break;
            case "add dish": {
                if (strAndDig.length < (j + 3))
                    throw new ExceptionAddDish();
                Event event = new EventAddDish(String.valueOf(strAndDig[j++]), Double.valueOf(String.valueOf(strAndDig[j++])), String.valueOf(strAndDig[j]));
                eventBus.post(event);
            }
            break;
            case "add category": {
                if (strAndDig.length < (j + 1))
                    throw new ExceptionNameCategory();
                Event event = new EventAddCategory(String.valueOf(strAndDig[j]));
                eventBus.post(event);
            }
            case "delete dish": {
                if (strAndDig.length < (j + 3))
                    throw new ExceptionNameDish();
                Event event = new EventDeleteDish(String.valueOf(strAndDig[j]));
                eventBus.post(event);
            }
            break;
            case "delete category": {
                if (strAndDig.length < (j + 3))
                    throw new ExceptionNameCategory();
                //ПРЕДУПРЕДИТЬ, ЧТО УДАЛЯТСЯ ВСЕ БЛЮДА ДАННОЙ КАТЕГОРИИ
                Event event = new EventDeleteCategory(String.valueOf(strAndDig[j]));
                eventBus.post(event);
            }
        }
    }
}
