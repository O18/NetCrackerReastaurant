package com.sanik3d.restaurant.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
/**
 * Created by Александр on 11.12.2016.
 */

@RunWith(MockitoJUnitRunner.class)
public class MenuTest {

    @Test
    public void getCategories() throws Exception {
        Menu menu = new Menu();
        assertEquals(0, menu.getCategories().size());
        menu.addCategory(new Category("1"));
        assertEquals(1, menu.getCategories().size());
    }

    @Test
    public void addCategory() throws Exception {
        Menu menu = new Menu();
        assertTrue(menu.addCategory(new Category("1")));
        assertFalse(menu.addCategory(new Category("1")));
        assertTrue(menu.addCategory(new Category("2")));
        assertEquals(2, menu.getCategories().size());
    }

    @Test
    public void addDishToCategory() throws Exception {
        Menu menu = new Menu();
        Category category1 = new Category("1");
        Dish dish1 = new Dish("1", "1", 1.0);
        Dish dish2 = new Dish("2", "2", 1.0);
        Category category2 = new Category("2");
        menu.addCategory(category1);
        assertTrue(menu.addDishToCategory(dish1, category1));
        assertFalse(menu.addDishToCategory(dish1, category1));
        assertFalse(menu.addDishToCategory(dish2, category2));
        menu.addCategory(category2);
        assertTrue(menu.addDishToCategory(dish2, category2));
        assertFalse(menu.addDishToCategory(dish1, category2));
        assertEquals(2, menu.getDishes().size());
    }

    @Test
    public void deleteCategory() throws Exception {
        Menu menu = new Menu();
        Category category1 = new Category("1");
        Category category2 = new Category("2");
        menu.addCategory(category1);
        assertTrue(menu.deleteCategory(category1));
        assertFalse(menu.deleteCategory(category1));
        assertFalse(menu.deleteCategory(category2));
        assertEquals(0, menu.getCategories().size());

        Dish dish = new Dish("1", "1", 1.0);
        Dish dish1 = new Dish("2", "1", 1.0);
        category1.addDish(dish);
        category1.addDish(dish1);
        menu.addCategory(category1);

        assertTrue(menu.deleteCategory(category1));
        assertEquals(0, menu.getDishes().size());
    }

    @Test
    public void deleteDishFromCategory() throws Exception {
       Menu menu = new Menu();
        Category category1 = new Category("1");
        Category category2 = new Category("2");
        Dish dish1 = new Dish("1", "1", 1.0);
        Dish dish2 = new Dish("2", "1", 1.0);
        menu.addCategory(category1);
        menu.addCategory(category2);
        menu.addDishToCategory(dish1, category1);
        menu.addDishToCategory(dish2, category1);
        assertTrue(menu.deleteDishFromCategory(dish1, category1));
        assertFalse(menu.deleteDishFromCategory(dish2, category2));
    }

    @Test
    public void getDishes() throws Exception {
        Menu menu = new Menu();
        Category category1 = new Category("1");
        Category category2 = new Category("2");
        menu.addCategory(category1);
        menu.addCategory(category2);
        menu.addDishToCategory(new Dish("1.1", "1", 1.0), category1);
        menu.addDishToCategory(new Dish("1.2", "1", 1.1), category1);
        menu.addDishToCategory(new Dish("2", "2", 1.1), category2);
        assertEquals(3, menu.getDishes().size());
    }

}