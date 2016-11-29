package com.sanik3d.restaurant.events;

import com.sanik3d.restaurant.eventbus.events.Event;
import com.sanik3d.restaurant.presenter.callbacks.AddDishCallback;

/**
 * Created by 1 on 29.11.2016.
 */
public class AddDishEvent implements Event {
    private String nameOfDish;
    private double priceOfDish;
    private String category;
    private AddDishCallback callback;

    public AddDishEvent(String nameOfDish, double priceOfDish, String category, AddDishCallback callback) {
        this.nameOfDish = nameOfDish;
        this.priceOfDish = priceOfDish;
        this.category = category;
        this.callback = callback;
    }

    public String getNameOfDish() {
        return nameOfDish;
    }

    public double getPriceOfDish() {
        return priceOfDish;
    }

    public String getCategory() {
        return category;
    }
}
