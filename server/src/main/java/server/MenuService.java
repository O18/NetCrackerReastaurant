package server;

import model.*;

import javax.ws.rs.WebApplicationException;
import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Created by User on 26.01.2017.
 */
class MenuService {
    private static final String EXTENSION = ".menu";
    private static final String CATALOG_PATH = "menus/";
    private static final String FILE_NAME = CATALOG_PATH + "Menus" + EXTENSION;
    private static final File FILE_WITH_MENUS = new File(FILE_NAME);
    static {
        try {
            if(FILE_WITH_MENUS.createNewFile()){
                setMenus(new HashMap<>());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static Set<String> getMenusNames(){
        return getMenus().keySet();
    }

    static MenuDTO getMenu(String path){
        Menu menu = loadMenu(path);
        return convertToDTO(menu);
    }

    private static Menu loadMenu(String name){
        //noinspection unchecked
        Map<String, Menu> menus = getMenus();
        Menu menu = menus.get(name);
        if(menu != null){
            return menu;
        }
        throw new WebApplicationException("Ошибка чтения. Меню с именем " + name + " не существует");
    }

    private static MenuDTO convertToDTO(Menu menu) {
        Set<CategoryDTO> categoryDTOS = new HashSet<>();
        for (Category category : menu.getCategories()) {
            Set<DishDTO> dishDTOS = new HashSet<>();
            for(Dish dish : category.getDishes()){
                dishDTOS.add(convertToDTO(dish));
            }
            categoryDTOS.add(new CategoryDTO(category.getName(), dishDTOS));
        }
        return new MenuDTO(categoryDTOS);
    }

    private static DishDTO convertToDTO(Dish dish) {
        return new DishDTO(dish.getName(), dish.getCost());
    }

    static void createMenu(String name) {
        Map<String, Menu> menus = getMenus();
        menus.put(name, new Menu());
        setMenus(menus);
    }

    static void addCategory(CategoryDTO categoryDTO, String name){
        if(categoryDTO.getName().equals(""))
            throw new WebApplicationException("Нельзя добавить категорию без имени");
        Menu menu = loadMenu(name);
        if(menu.addCategory(new Category(categoryDTO.getName()))) {
            saveMenu(name, menu);
        } else {
            throw new WebApplicationException("Категория с именем " + categoryDTO.getName() + " уже существует");
        }
    }

    static void addDish(DishDTO dishDTO, String categoryName, String menuName){
        Menu menu = loadMenu(menuName);
        Category category = getCategoryByName(categoryName, menu);
        if(!menu.addDishToCategory(convertToModel(dishDTO), category)){
            throw new WebApplicationException("Блюдо с именем " + dishDTO.getDishName() + " уже существует");
        }

        saveMenu(menuName, menu);
    }

    static void deleteCategory(String categoryName, String menuName){
        Menu menu = loadMenu(menuName);
        if(!menu.deleteCategory(getCategoryByName(categoryName, menu))){
            throw new WebApplicationException("Категории с именем " + categoryName + " не существует в меню в файле " + menuName);
        }

        saveMenu(menuName, menu);
    }

    static void deleteDish(String dishName, String categoryName, String menuName){
        Menu menu = loadMenu(menuName);
        Category category = getCategoryByName(categoryName, menu);
        Dish dish = getDishByName(dishName, category);
        if(!menu.deleteDishFromCategory(dish, category)){
            throw new WebApplicationException("Блюда с именем " + dishName + " не существует в категории " + categoryName + " в файле " + menuName);
        }

        saveMenu(menuName, menu);
    }

    static void changeCategory(String newCategoryName, String oldCategoryName, String menuName){
        Menu menu = loadMenu(menuName);
        Category oldCategory = getCategoryByName(oldCategoryName, menu);
        if(menu.getCategories().contains(oldCategory)){
            oldCategory.setName(newCategoryName);
        }
        else {
            throw new WebApplicationException("Категории с именем " + oldCategoryName + " не существует в меню в файле " + menuName);
        }

        saveMenu(menuName, menu);
    }

    static void changeDish(DishDTO newDishDTO, String oldDishName, String categoryName, String menuName){
        Menu menu = loadMenu(menuName);
        Category category = getCategoryByName(categoryName, menu);
        Dish dish = getDishByName(oldDishName, category);
        if(menu.deleteDishFromCategory(dish, category)){
            menu.addDishToCategory(convertToModel(newDishDTO), category);
        }
        else {
            throw new WebApplicationException("Блюда с именем " + oldDishName + " не существует в категории " + categoryName + " в файле " + menuName);
        }

        saveMenu(menuName, menu);
    }

    private static Dish getDishByName(String dishName, Category category) {
        for(Dish dish : category.getDishes()){
            if(dish.getName().equals(dishName)){
                return dish;
            }
        }

        throw new WebApplicationException("Блюдо с именем " + dishName + " не найдено в категории " + category);
    }

    private static Category getCategoryByName(String categoryName, Menu menu) {
        for (Category category: menu.getCategories()) {
            if(category.getName().equals(categoryName)){
                return category;
            }
        }

        throw new WebApplicationException("Категории с именем " + categoryName + " не найдено в меню " + menu);
    }

    private static Dish convertToModel(DishDTO dishDTO) {
        return new Dish(dishDTO.getDishName(), dishDTO.getCost());
    }

    private static void saveMenu(String name, Menu menu) {
        Map<String, Menu> menus = getMenus();
        menus.put(name, menu);
        setMenus(menus);
    }

    static void deleteMenu(String menuName) {
        Map<String, Menu> menus = getMenus();

        if(menus.remove(menuName) != null){
            throw new WebApplicationException("Удаление несуществующего файла " + menuName);
        }
    }

    private static Map<String, Menu> getMenus(){
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_WITH_MENUS))){
            //noinspection unchecked
            return (Map) in.readObject();
        } catch (FileNotFoundException e) {
            throw new WebApplicationException("Невозможно найти файл", e);
        } catch (IOException | ClassNotFoundException e) {
            throw new WebApplicationException("Ошибка чтения", e);
        }
    }

    private static void setMenus(Map<String, Menu> menus){
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_WITH_MENUS))){
            //noinspection unchecked
            out.writeObject(menus);
        } catch (FileNotFoundException e) {
            throw new WebApplicationException("Невозможно найти файл", e);
        } catch (IOException e) {
            throw new WebApplicationException("Ошибка чтения", e);
        }
    }
}