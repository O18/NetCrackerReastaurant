package com.o18.restaurant.model;

import java.io.Serializable;

/**
 * Created by Александр on 06.11.2016.
 */
public class Dish implements Serializable{
    private static final long serialVersionUID = 4245136608929768391L;

    private String name;
    private String categoryName;
    private double cost;

    public Dish(){

    }

    public Dish(String name, String categoryName, double cost) {
        this.name = name;
        this.categoryName = categoryName;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public double getCost() {
        return cost;
    }

    void setName(String name) {
        this.name = name;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dish dish = (Dish) o;

        return Double.compare(dish.cost, cost) == 0 && name.equals(dish.name) && categoryName.equals(dish.categoryName);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name.hashCode();
        result = 31 * result + categoryName.hashCode();
        temp = Double.doubleToLongBits(cost);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
