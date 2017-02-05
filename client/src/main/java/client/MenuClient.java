package client;

import model.CategoryDTO;
import model.DishDTO;
import model.MenuDTO;
import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.util.Set;
import java.util.StringJoiner;

/**
 * Created by User on 23.01.2017.
 */
public class MenuClient {
    private static final String MENUS = "menus";
    private WebTarget target;

    public MenuClient(){
        Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();
        target = client.target("http://localhost:2222").path(MENUS);
    }

    public Set<String> getMenusNames(){
        try {
            //noinspection unchecked
            return (Set<String>) target.request(MediaType.APPLICATION_JSON_TYPE).get(Set.class);
        } catch (InternalServerErrorException e){
            throw new ServerException(e.getResponse().readEntity(String.class));
        }
    }

    public MenuDTO getMenu(String menuFileName){
        try {
            return target.path(menuFileName).request(MediaType.APPLICATION_JSON_TYPE).get(MenuDTO.class);
        } catch (InternalServerErrorException e){
            throw new ServerException(e.getResponse().readEntity(String.class));
        }
    }

    public void createMenu(String menuFileName){
        try {
            target.path(menuFileName).request().post(Entity.json(null));
        } catch (InternalServerErrorException e){
            throw new ServerException(e.getResponse().readEntity(String.class));
        }
    }

    public void deleteMenu(String menuFileName){
        try {
            target.path(menuFileName).request().delete();
        } catch (InternalServerErrorException e){
            throw new ServerException(e.getResponse().readEntity(String.class));
        }
    }

    public void addCategory(CategoryDTO categoryDTO, String menuName){
        try {
            target.path(menuName).request().put(Entity.entity(categoryDTO, MediaType.APPLICATION_JSON_TYPE));
        } catch (InternalServerErrorException e){
            throw new ServerException(e.getResponse().readEntity(String.class));
        }
    }

    public void addDish(DishDTO dishDTO, String categoryName,  String menuName){
        try {
            target.path(menuName).path(categoryName).request().put(Entity.entity(dishDTO, MediaType.APPLICATION_JSON_TYPE));
        } catch (InternalServerErrorException e){
            throw new ServerException(e.getResponse().readEntity(String.class));
        }
    }

    public void changeCategory(String newCategoryName, String oldCategoryName,  String menuName){
        try {
            target.path(menuName).path(oldCategoryName).request().post(Entity.entity(newCategoryName, MediaType.TEXT_PLAIN_TYPE));
        } catch (InternalServerErrorException e){
            throw new ServerException(e.getResponse().readEntity(String.class));
        }
    }

    public void changeDish(DishDTO dishDTO, String oldDishName, String categoryName, String menuName){
        try {
            target.path(menuName).path(categoryName).path(oldDishName).request().post(Entity.entity(dishDTO, MediaType.APPLICATION_JSON_TYPE));
        } catch (InternalServerErrorException e){
            throw new ServerException(e.getResponse().readEntity(String.class));
        }
    }

    public void deleteCategory(String categoryName, String menuName){
        try {
            target.path(menuName).path(categoryName).request().delete();
        } catch (InternalServerErrorException e){
            throw new ServerException(e.getResponse().readEntity(String.class));
        }
    }

    public void deleteDish(String dishName, String categoryName, String menuName){
        try {
            target.path(menuName).path(categoryName).path(dishName).request().delete();
        } catch (InternalServerErrorException e){
            throw new ServerException(e.getResponse().readEntity(String.class));
        }
    }
}
