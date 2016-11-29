package com.sanik3d.restaurant.model;

import java.io.Serializable;

/**
 * Created by Александр on 06.11.2016.
 */
public class Dish implements Serializable{
    private static final long serialVersionUID = 1L;

    private final String name;
    private final Category category;//todo
    private final double cost;

    public Dish(String name, Category category, double cost) {
        this.name = name;
        this.category = category;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public double getCost() {
        return cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dish dish = (Dish) o;

        if (Double.compare(dish.cost, cost) != 0) return false;
        if (!name.equals(dish.name)) return false;
        return category.equals(dish.category);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name.hashCode();
        result = 31 * result + category.hashCode();
        temp = Double.doubleToLongBits(cost);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
