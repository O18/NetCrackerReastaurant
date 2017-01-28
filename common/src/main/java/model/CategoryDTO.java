package model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by User on 25.01.2017.
 */
public class CategoryDTO implements Serializable {
    private static final long serialVersionUID = -7334061331560529720L;

    private String name;
    private Set<DishDTO> dishes;

    public CategoryDTO(String name, Set<DishDTO> dishes) {
        this.name = name;
        this.dishes = dishes;
    }

    public CategoryDTO(String name){
        this.name = name;
        dishes = new HashSet<>();
    }

    public CategoryDTO() {
        name = null;
        dishes = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public Set<DishDTO> getDishes() {
        return dishes;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDishes(Set<DishDTO> dishes) {
        this.dishes = dishes;
    }

    @Override
    public String toString() {
        return name;
    }
}
