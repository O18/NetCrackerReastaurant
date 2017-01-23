package com.o18.restaurant.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by User on 23.01.2017.
 */
public class MenuTest {
    Menu menu;

    @Before
    public void setUp() throws Exception {
        menu = new Menu();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void setCategories() throws Exception {
        Set<Category> categories = new HashSet<>();
        categories.add(new Category("Мясо"));
        categories.add(new Category("Суп"));

        menu.setCategories(categories);
    }

    @Test
    public void getCategories() throws Exception {

    }

    @Test
    public void addDishToCategory() throws Exception {

    }

    @Test
    public void deleteDishFromCategory() throws Exception {

    }

    @Test
    public void addCategory() throws Exception {

    }

    @Test
    public void deleteCategory() throws Exception {

    }

    @Test
    public void setCategoryName() throws Exception {

    }

    @Test
    public void setDishName() throws Exception {

    }

    @Test
    public void setDishCost() throws Exception {

    }

    @Test
    public void getDishes() throws Exception {

    }

}