package com.sanik3d.restaurant.model;

import java.io.Serializable;

/**
 * Created by Александр on 06.11.2016.
 */
public class Category implements Serializable{
    private static final long serialVersionUID = 1L;

    private String name;

    public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        return name.equals(category.name);

    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
