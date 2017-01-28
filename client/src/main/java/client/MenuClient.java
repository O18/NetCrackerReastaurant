package client;

import model.CategoryDTO;
import model.DishDTO;
import model.MenuDTO;
import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.util.Set;

/**
 * Created by User on 23.01.2017.
 */
public class MenuClient {
    private static final String MENUS = "menus";
    private WebTarget target;

    public static void main(String[] args) {
        MenuClient client = new MenuClient();
        Set<String> names = client.getMenusNames();
        DishDTO dishDTO = new DishDTO("Кролик", 250);
        //client.addDish(dishDTO, "Мясо", "newMenu");
        //client.deleteDish("Кролик", "Мясо", "newMenu");
        //client.addCategory(new CategoryDTO("Рыба"), "newMenu");
        //client.addDish(new DishDTO("Форель", 500), "Рыба", "newMenu");
        client.changeDish(new DishDTO("Форель красная", 550), "Форель", "Рыба", "newMenu");
        //client.deleteCategory("Рыба", "newMenu");
        //client.changeCategory(new CategoryDTO("Горячее"), "Мясо", "newMenu");

        MenuDTO menu = client.getMenu("newMenu");
        System.out.println(menu);
    }

    public MenuClient(){
        Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();
        target = client.target("http://localhost:2222").path(MENUS);
    }

    public Set<String> getMenusNames(){
        //noinspection unchecked
        return (Set<String>) target.request(MediaType.APPLICATION_JSON_TYPE).get(Set.class);
    }

    public MenuDTO getMenu(String menuFileName){
        return target.path(menuFileName).request(MediaType.APPLICATION_JSON_TYPE).get(MenuDTO.class);
    }

    public void createMenu(String menuFileName){
        target.path(menuFileName).request().get();
    }

    public void deleteMenu(String menuFileName){
        target.path(menuFileName).request().delete();
    }

    public void addCategory(CategoryDTO categoryDTO, String menuName){
        target.path(menuName).request().put(Entity.entity(categoryDTO, MediaType.APPLICATION_JSON_TYPE));
    }

    public void addDish(DishDTO dishDTO, String categoryName,  String menuName){
        target.path(menuName).path(categoryName).request().put(Entity.entity(dishDTO, MediaType.APPLICATION_JSON_TYPE));
    }

    public void changeCategory(CategoryDTO newCategoryDTO, String oldCategoryName,  String menuName){
        target.path(menuName).path(oldCategoryName).request().post(Entity.entity(newCategoryDTO, MediaType.APPLICATION_JSON_TYPE));
    }

    public void changeDish(DishDTO dishDTO, String oldDishName, String categoryName, String menuName){
        target.path(menuName).path(categoryName).path(oldDishName).request().post(Entity.entity(dishDTO, MediaType.APPLICATION_JSON_TYPE));
    }

    public void deleteCategory(String categoryName, String menuName){
        target.path(menuName).path(categoryName).request().delete();
    }

    public void deleteDish(String dishName, String categoryName, String menuName){
        target.path(menuName).path(categoryName).path(dishName).request().delete();
    }
}
