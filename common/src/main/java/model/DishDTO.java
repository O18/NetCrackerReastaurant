package model;

import java.io.Serializable;

/**
 * Created by User on 25.01.2017.
 */
public class DishDTO implements Serializable{
    private static final long serialVersionUID = -9170204913849621379L;

    private String dishName;
    private double cost;

    public DishDTO(String dishName, double cost) {
        this.dishName = dishName;
        this.cost = cost;
    }

    public DishDTO() {
    }

    public String getDishName() {
        return dishName;
    }

    public double getCost() {
        return cost;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "DishDTO{" +
                "dishName='" + dishName + '\'' +
                ", cost=" + cost +
                '}';
    }
}
