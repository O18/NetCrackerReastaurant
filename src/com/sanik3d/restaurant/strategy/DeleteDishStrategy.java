package com.sanik3d.restaurant.strategy;

import com.sanik3d.restaurant.events.DeleteDishEvent;
import com.sanik3d.restaurant.events.Event;
import com.sanik3d.restaurant.presenter.callbacks.DeleteDishCallback;
import com.sanik3d.restaurant.view.View;

/**
 * Created by 1 on 05.12.2016.
 */
public class DeleteDishStrategy implements Strategy{
    private View view;

    public DeleteDishStrategy(View view) {
        this.view = view;
    }

    public Event createEvent(String[] commandString) {
        return new DeleteDishEvent(commandString[0], new DeleteDishCallback() {
            View view = new View();//пока что

            @Override
            public void onSuccess() {
                view.print("Удаление блюда прошло успешно!");
            }

            @Override
            public void onFail(RuntimeException e) {
                view.print(e.getMessage() + ' ' + e.getCause());
            }
        });
    }
}
