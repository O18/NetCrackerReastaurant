package com.sanik3d.restaurant.strategy;

import com.sanik3d.restaurant.events.AddCategoryEvent;
import com.sanik3d.restaurant.events.Event;
import com.sanik3d.restaurant.presenter.callbacks.AddCategoryCallback;
import com.sanik3d.restaurant.view.View;

/**
 * Created by 1 on 04.12.2016.
 */
public class AddCategoryStrategy implements Strategy {//в презенте рперенести  + переделать в креатор.фабрику  todo
    public AddCategoryStrategy(View view) {
        this.view = view;
    }

    private View view;

    @Override
    public Event createEvent(String[] commandString) {
        //добавить проверки с адекватными юзер мессаджами todo
        return new AddCategoryEvent(commandString[0], new AddCategoryCallback() {

            @Override
            public void onSuccess() {
                view.print("Добавление категории прошло успешно!");
            }

            @Override
            public void onFail(RuntimeException e) {
                view.print(e.getMessage() + ' ' + e.getCause());
            }
        });
    }
}
