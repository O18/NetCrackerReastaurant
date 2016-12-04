package com.sanik3d.restaurant.strategy;

import com.sanik3d.restaurant.events.Event;
import com.sanik3d.restaurant.events.SaveMenuEvent;
import com.sanik3d.restaurant.presenter.callbacks.SaveMenuCallback;
import com.sanik3d.restaurant.view.View;

/**
 * Created by 1 on 05.12.2016.
 */
public class SaveMenuStrategy implements Strategy {
    View view;

    public SaveMenuStrategy(View view) {
        this.view = view;
    }

    public Event createEvent(String[] commandString){
        return new SaveMenuEvent(commandString[0], new SaveMenuCallback() {
            View view = new View();//пока что

            @Override
            public void onSuccess() {
                view.print("Соханение меню прошло успешно!");
            }

            @Override
            public void onFail(RuntimeException e) {
                view.print(e.getMessage() + ' ' + e.getCause());
            }
        });
    }
}
