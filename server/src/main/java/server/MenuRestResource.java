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

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Set<String> getMenusList() {
        return MenuService.getMenusNames();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{menu_name}")
    public MenuDTO getMenu(@PathParam("menu_name") String menuName) {
        return MenuService.getMenu(menuName);
    }

    @POST
    @Path("{menu_name}")
    public void createMenu(@PathParam("menu_name") String menuName) {
        MenuService.createMenu(menuName);
    }


    @DELETE
    @Path("{menu_name}")
    public void deleteMenu(@PathParam("menu_name") String menuName) {
        MenuService.deleteMenu(menuName);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{menu_name}")
    public void addCategory(CategoryDTO categoryDTO, @PathParam("menu_name") String menuName) {
        MenuService.addCategory(categoryDTO, menuName);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{menu_name}/{category_name}")
    public void addDish(DishDTO dishDTO, @PathParam("category_name") String categoryName, @PathParam("menu_name") String menuName) {
        MenuService.addDish(dishDTO, categoryName, menuName);
    }

    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Path("{menu_name}/{category_name}")
    public void changeCategory(String newCategoryName, @PathParam("category_name") String oldCategoryName, @PathParam("menu_name") String menuName) {
        MenuService.changeCategory(newCategoryName, oldCategoryName, menuName);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{menu_name}/{category_name}/{dish_name}")
    public void changeDish(DishDTO dishDTO, @PathParam("dish_name") String oldDishName, @PathParam("category_name") String categoryName, @PathParam("menu_name") String menuName) {
        MenuService.changeDish(dishDTO, oldDishName, categoryName, menuName);
    }

    @DELETE
    @Path("{menu_name}/{category_name}")
    public void deleteCategory(@PathParam("category_name") String categoryName, @PathParam("menu_name") String menuName) {
        MenuService.deleteCategory(categoryName, menuName);
    }

    @DELETE
    @Path("{menu_name}/{category_name}/{dish_name}")
    public void deleteDish(@PathParam("dish_name") String dishName, @PathParam("category_name") String categoryName, @PathParam("menu_name") String menuName) {
        MenuService.deleteDish(dishName, categoryName, menuName);
    }
}