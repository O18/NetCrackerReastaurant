package com.sanik3d.restaurant.strategy;


import com.sanik3d.restaurant.events.DeleteCategoryEvent;
import com.sanik3d.restaurant.events.Event;
import com.sanik3d.restaurant.presenter.callbacks.DeleteCategoryCallback;
import com.sanik3d.restaurant.view.View;

/**
 * Created by 1 on 05.12.2016.
 */
public class DeleteCategoryStrategy implements Strategy {
    public DeleteCategoryStrategy(View view) {
        this.view = view;
    }

    private View view;
    public Event createEvent(String[] commandString) {
        return new DeleteCategoryEvent(commandString[0], new DeleteCategoryCallback() {

            @Override
            public void onSuccess() {
                view.print("Удаление категории прошло успешно!");
            }

            @Override
            public void onFail(RuntimeException e) {
                view.print(e.getMessage() + ' ' + e.getCause());
            }
        });
    }
}
