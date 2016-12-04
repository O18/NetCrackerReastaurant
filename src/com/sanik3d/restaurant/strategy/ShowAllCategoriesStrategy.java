package com.sanik3d.restaurant.strategy;

import com.sanik3d.restaurant.events.Event;
import com.sanik3d.restaurant.events.ShowAllCategoriesEvent;
import com.sanik3d.restaurant.model.Category;
import com.sanik3d.restaurant.presenter.callbacks.ShowAllCategoriesCallback;
import com.sanik3d.restaurant.view.View;

import java.util.Set;

/**
 * Created by 1 on 05.12.2016.
 */
public class ShowAllCategoriesStrategy implements Strategy {
    View view;

    public ShowAllCategoriesStrategy(View view) {
        this.view = view;
    }

    public Event createEvent(String[] commandString) {
        return new ShowAllCategoriesEvent(new ShowAllCategoriesCallback() {
            View view = new View();//пока что

            @Override
            public void onSuccess(Set<Category> ts) {
                view.print(ts.toString());
            }

            @Override
            public void onFail() {
                view.print("Неудача!");
            }
        });
    }
}
