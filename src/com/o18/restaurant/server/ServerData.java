package com.o18.restaurant.server;

import com.o18.restaurant.model.Category;
import com.o18.restaurant.model.Dish;
import com.o18.restaurant.model.Menu;

import java.io.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Created by User on 22.01.2017.
 */
class ServerData {
    private static final String regex = ".*.menu";
    private static final String CATALOG_PATH = "menus/";

    private final Set<String> namesOfMenus;

    ServerData(){
        namesOfMenus = new HashSet<>();
        loadMenus();
    }

    private void loadMenus() {
        namesOfMenus.clear();
        File path = new File(CATALOG_PATH);
        String[] list = path.list((dir, name) -> Pattern.compile(regex).matcher(name).matches());
        if (list != null) {
            Collections.addAll(namesOfMenus, list);
        }
    }

    Set<String> getNamesOfMenus() {
        return namesOfMenus;
    }

    Menu getMenuWithName(String menuName) {
        File path = new File(CATALOG_PATH + menuName);
        try {
            if(!path.createNewFile()){
                return new Menu();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return loadMenu(menuName);

    }

    private Menu loadMenu(String menuName) {
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(CATALOG_PATH + menuName))){
            return  (Menu)in.readObject();
        }catch (FileNotFoundException e){
            throw new RuntimeException("Файла с таким именем не существует");
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Ошибка чтения");
        }
    }


    void saveMenu(Menu menu, String menuName){
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(CATALOG_PATH + menuName))) {
            out.writeObject(menu);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Файла "+ menuName +" не найден");
        } catch (IOException e) {
            throw new RuntimeException("Ошибка чтения");
        }
    }

    boolean deleteMenu(String menuName){
        File file = new File(CATALOG_PATH + menuName);
        return file.delete();
    }
}