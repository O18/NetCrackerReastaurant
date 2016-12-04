package com.sanik3d.restaurant.strategy;

import com.sanik3d.restaurant.events.Event;
import com.sanik3d.restaurant.events.LoadMenuInMemoryEvent;
import com.sanik3d.restaurant.presenter.callbacks.LoadMenuInMemoryCallback;
import com.sanik3d.restaurant.view.View;

/**
 * Created by 1 on 05.12.2016.
 */
public class LoadMenuInMemoryStrategy implements Strategy {
    public LoadMenuInMemoryStrategy(View view) {
        this.view = view;
    }

    View view;
    public Event createEvent(String[] commandString) {
        return new LoadMenuInMemoryEvent(commandString[0], new LoadMenuInMemoryCallback() {
            View view = new View();//пока что

            @Override
            public void onSuccess() {
                view.print("Загрузка меню в память прошла успешно!");
            }

            @Override
            public void onFail(RuntimeException e) {
                view.print(e.getMessage() + ' ' + e.getCause());
            }
        });
    }
}
