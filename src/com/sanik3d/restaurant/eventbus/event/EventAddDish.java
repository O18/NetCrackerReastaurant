package com.sanik3d.restaurant.eventbus.event;

/**
 * Created by 1 on 14.11.2016.
 */
public class EventAddDish implements Event {
    private String nameOfDish;
    private double priceOfDish;
    private String category;
    public EventAddDish(String nameOfDish, double priceOfDish, String category) {
        this.nameOfDish = nameOfDish;
        this.priceOfDish = priceOfDish;
        this.category = category;
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
