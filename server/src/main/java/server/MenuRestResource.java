package server;


import model.CategoryDTO;
import model.DishDTO;
import model.MenuDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Set;

/**
 * Created by Александр on 13.01.2017.
 */

@Path("menus")
public class MenuRestResource {
    private static final String EXTENSION = ".menu";
    private static final String CATALOG_PATH = "menus/";

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Set<String> getMenusList() {
        return MenuService.getMenusNames(CATALOG_PATH, EXTENSION);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{menu_name}")
    public MenuDTO getMenu(@PathParam("menu_name") String menuName) {
        return MenuService.getMenu(CATALOG_PATH + menuName + EXTENSION);
    }

    @POST
    @Path("{menu_name}")
    public void createMenu(@PathParam("menu_name") String menuName) {
        MenuService.createMenu(CATALOG_PATH + menuName + EXTENSION);
    }


    @DELETE
    @Path("{menu_name}")
    public void deleteMenu(@PathParam("menu_name") String menuName) {
        MenuService.deleteMenu(CATALOG_PATH + menuName + EXTENSION);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{menu_name}")
    public void addCategory(CategoryDTO categoryDTO, @PathParam("menu_name") String menuName) {
        MenuService.addCategory(categoryDTO, CATALOG_PATH + menuName + EXTENSION);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{menu_name}/{category_name}")
    public void addDish(DishDTO dishDTO, @PathParam("category_name") String categoryName, @PathParam("menu_name") String menuName) {
        MenuService.addDish(dishDTO, categoryName, CATALOG_PATH + menuName + EXTENSION);
    }

    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Path("{menu_name}/{category_name}")
    public void changeCategory(String newCategoryName, @PathParam("category_name") String oldCategoryName, @PathParam("menu_name") String menuName) {
        MenuService.changeCategory(newCategoryName, oldCategoryName, CATALOG_PATH + menuName + EXTENSION);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{menu_name}/{category_name}/{dish_name}")
    public void changeDish(DishDTO dishDTO, @PathParam("dish_name") String oldDishName, @PathParam("category_name") String categoryName, @PathParam("menu_name") String menuName) {
        MenuService.changeDish(dishDTO, oldDishName, categoryName, CATALOG_PATH + menuName + EXTENSION);
    }

    @DELETE
    @Path("{menu_name}/{category_name}")
    public void deleteCategory(@PathParam("category_name") String categoryName, @PathParam("menu_name") String menuName) {
        MenuService.deleteCategory(categoryName, CATALOG_PATH + menuName + EXTENSION);
    }

    @DELETE
    @Path("{menu_name}/{category_name}/{dish_name}")
    public void deleteDish(@PathParam("dish_name") String dishName, @PathParam("category_name") String categoryName, @PathParam("menu_name") String menuName) {
        MenuService.deleteDish(dishName, categoryName, CATALOG_PATH + menuName + EXTENSION);
    }
}