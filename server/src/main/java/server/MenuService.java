package server;

import model.*;

import java.io.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Created by User on 26.01.2017.
 */
class MenuService {

    static Set<String> getMenusNames(String catalogPath, String extension){
        Set<String> menusNames = new HashSet<>();
        File path = new File(catalogPath);
        String[] list = path.list((dir, name) -> Pattern.compile(".*" + extension).matcher(name).matches());

        if (list != null) {
            for (int i = 0; i < list.length; i++) {
                list[i] = list[i].replace(extension, "");
            }

            Collections.addAll(menusNames, list);
        }

        return menusNames;
    }

    static MenuDTO getMenu(String path){
        Menu menu = loadMenu(path);
        return convertToDTO(menu);
    }

    private static Menu loadMenu(String path){
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(path))){
            return (Menu) in.readObject();
        }catch (FileNotFoundException e){
            throw new RuntimeException("Файла с таким именем не существует", e);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Ошибка чтения", e);
        }
    }

    private static MenuDTO convertToDTO(Menu menu) {
        Set<CategoryDTO> categoryDTOS = new HashSet<>();
        for (Category category : menu.getCategories()) {
            categoryDTOS.add(convertToDTO(category));
        }
        return new MenuDTO(categoryDTOS);
    }

    private static CategoryDTO convertToDTO(Category category) {
        Set<DishDTO> dishDTOS = new HashSet<>();
        for(Dish dish : category.getDishes()){
            DishDTO dishDTO = convertToDTO(dish);
            dishDTOS.add(dishDTO);
        }

        return new CategoryDTO(category.getName(), dishDTOS);
    }

    private static DishDTO convertToDTO(Dish dish) {
        return new DishDTO(dish.getName(), dish.getCost());
    }

    static void createMenu(String menuPath) {
        File file = new File(menuPath);
        try{
            //noinspection ResultOfMethodCallIgnored
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при создании файла", e);
        }

        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(menuPath))) {
            out.writeObject(new Menu());
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Файл не найден", e);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка записи меню в файл с именем " + menuPath, e);
        }
    }

    static void addCategory(CategoryDTO categoryDTO, String menuPath){
        Menu menu = loadMenu(menuPath);
        menu.addCategory(convertToModel(categoryDTO));

        saveMenu(menuPath, menu);
    }

    static void addDish(DishDTO dishDTO, String categoryName, String menuPath){
        Menu menu = loadMenu(menuPath);
        Category category = getCategoryByName(categoryName, menu);
        menu.addDishToCategory(convertToModel(dishDTO), category);

        saveMenu(menuPath, menu);
    }

    static void deleteCategory(String categoryName, String menuPath){
        Menu menu = loadMenu(menuPath);
        if(!menu.deleteCategory(getCategoryByName(categoryName, menu))){
            throw new RuntimeException("Категории с именем " + categoryName + " не существует в меню в файле " + menuPath);
        }

        saveMenu(menuPath, menu);
    }

    static void deleteDish(String dishName, String categoryName, String menuPath){
        Menu menu = loadMenu(menuPath);
        Category category = getCategoryByName(categoryName, menu);
        Dish dish = getDishByName(dishName, category);
        if(!menu.deleteDishFromCategory(dish, category)){
            throw new RuntimeException("Блюда с именем " + dishName + " не существует в категории " + categoryName + " в файле " + menuPath);
        }

        saveMenu(menuPath, menu);
    }

    static void changeCategory(CategoryDTO newCategoryDTO, String oldCategoryName, String menuPath){
        Menu menu = loadMenu(menuPath);
        Category oldCategory = getCategoryByName(oldCategoryName, menu);
        if(menu.deleteCategory(oldCategory)){
            menu.addCategory(convertToModel(newCategoryDTO));
        }
        else {
            throw new RuntimeException("Категории с именем " + oldCategoryName + " не существует в меню в файле " + menuPath);
        }

        saveMenu(menuPath, menu);
    }

    static void changeDish(DishDTO newDishDTO, String oldDishName, String categoryName, String menuPath){
        Menu menu = loadMenu(menuPath);
        Category category = getCategoryByName(categoryName, menu);
        Dish dish = getDishByName(oldDishName, category);
        if(menu.deleteDishFromCategory(dish, category)){
            menu.addDishToCategory(convertToModel(newDishDTO), category);
        }
        else {
            throw new RuntimeException("Блюда с именем " + oldDishName + " не существует в категории " + categoryName + " в файле " + menuPath);
        }

        saveMenu(menuPath, menu);
    }

    private static Dish getDishByName(String dishName, Category category) {
        for(Dish dish : category.getDishes()){
            if(dish.getName().equals(dishName)){
                return dish;
            }
        }

        throw new RuntimeException("Блюдо с именем " + dishName + " не найдено в категории " + category);
    }

    private static Category getCategoryByName(String categoryName, Menu menu) {
        for (Category category: menu.getCategories()) {
            if(category.getName().equals(categoryName)){
                return category;
            }
        }

        throw new RuntimeException("Категории с именем " + categoryName + " не найдено в меню " + menu);
    }

    private static Dish convertToModel(DishDTO dishDTO) {
        return new Dish(dishDTO.getDishName(), dishDTO.getCost());
    }

    private static void saveMenu(String menuPath, Menu menu) {
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(menuPath))) {
            out.writeObject(menu);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Файл не найден", e);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка записи меню в файл с именем " + menuPath, e);
        }
    }

    private static Category convertToModel(CategoryDTO categoryDTO) {
        Set<Dish> dishes = new HashSet<>();
        for(DishDTO dishDTO : categoryDTO.getDishes()){
            Dish dish = new Dish(dishDTO.getDishName(), dishDTO.getCost());
            dishes.add(dish);
        }

        return new Category(categoryDTO.getName(), dishes);
    }


    static void deleteMenu(String menuName) {
        File file = new File(menuName);
        if(!file.delete()){
            throw new RuntimeException("Удаление несуществующего файла " + menuName);
        }
    }
}