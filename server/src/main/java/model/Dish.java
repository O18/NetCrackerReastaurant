package model;

import java.io.Serializable;

/**
 * Created by Александр on 06.11.2016.
 */
public class Dish implements Serializable{
    private static final long serialVersionUID = -3264442724863657886L;

    private String name;
    private double cost;

    public Dish(){

    }

    public Dish(String name, double cost) {
        this.name = name;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }


    public double getCost() {
        return cost;
    }

    void setName(String name) {
        this.name = name;
    }

    void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dish dish = (Dish) o;

        return Double.compare(dish.cost, cost) == 0 && name.equals(dish.name);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name.hashCode();
        temp = Double.doubleToLongBits(cost);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
