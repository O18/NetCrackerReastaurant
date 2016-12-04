package com.sanik3d.restaurant.strategy;

import com.sanik3d.restaurant.events.AddDishEvent;
import com.sanik3d.restaurant.events.Event;
import com.sanik3d.restaurant.presenter.callbacks.AddDishCallback;
import com.sanik3d.restaurant.view.View;

/**
 * Created by 1 on 04.12.2016.
 */
public class AddDishStrategy implements Strategy {
    private View view;
    public AddDishStrategy(View view) {
        this.view = view;
    }

    @Override
    public Event createEvent(String[] commandString) {
        return new AddDishEvent(commandString[0], Double.valueOf(commandString[1]), commandString[2], new AddDishCallback() {
            @Override
            public void onSuccess() {
                view.print("Добавление блюда прошло успешно!");
            }

            @Override
            public void onFail(RuntimeException e) {
                view.print(e.getMessage()+' '+e.getCause());
            }
        });
    }
}
