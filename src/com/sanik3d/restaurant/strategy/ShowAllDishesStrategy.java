package com.sanik3d.restaurant.strategy;

import com.sanik3d.restaurant.events.Event;
import com.sanik3d.restaurant.events.ShowAllDishesEvent;
import com.sanik3d.restaurant.model.Dish;
import com.sanik3d.restaurant.presenter.callbacks.ShowAllDishesCallback;
import com.sanik3d.restaurant.view.View;

import java.util.Set;

/**
 * Created by 1 on 05.12.2016.
 */
public class ShowAllDishesStrategy implements Strategy {
    View view;

    public ShowAllDishesStrategy(View view) {
        this.view = view;
    }

    public Event createEvent(String[] commandString) {
        return new ShowAllDishesEvent(new ShowAllDishesCallback() {
            View view = new View();//пока что

            @Override
            public void onSuccess(Set<Dish> dishes) {
                StringBuilder resultString = new StringBuilder();
                for (Dish dish: dishes) {
                    resultString.append(dish.toString()).append('\n');
                }

                view.print(resultString.toString());
            }

            @Override
            public void onFail() {
                view.print("Неудача!");
            }
        });
    }
}
