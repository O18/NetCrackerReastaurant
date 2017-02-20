package model;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Created by Александр on 06.11.2016.
 */
public class Category implements Serializable{
    private static final long serialVersionUID = -2408931504592493532L;

    private String name;
    private final Set<Dish> dishes;

    public Category(){
        dishes = new HashSet<>();
    }

    public Category(String name){
        this.name = name;
        dishes = new HashSet<>();
    }

    public Category(String name, Set<Dish> dishes) {
        this.name = name;
        this.dishes = dishes;
    }

    public String getName() {
        return name;
    }

    public boolean setName(String newName){
        if (Objects.equals(newName, name))
            return false;
        name = newName;

        return true;
    }

    boolean addDish(Dish dish){
        for(Dish d : dishes){
            if(d.getName().equals(dish.getName())){
                return false;
            }
        }
        return dishes.add(dish);
    }

    public Set<Dish> getDishes() {
        return Collections.unmodifiableSet(dishes);
    }

    boolean deleteDish(Dish dish){
        return dishes.remove(dish);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        return name.equals(category.name) && dishes.equals(category.dishes);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return name;
    }
}
