package com.o18.restaurant.server;

import com.o18.restaurant.model.Category;
import com.o18.restaurant.model.Menu;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.Set;

/**
 * Created by Александр on 13.01.2017.
 */

@Path("menus")
public class MenuRestService {
    @Context
    Set<Menu> menus;

    @GET
    @Path("get")
    @Produces(MediaType.APPLICATION_JSON)
    public Menu getMenus() {
        Menu menu = new Menu();
        menu.addCategory(new Category("Мяско"));
        return menu;
    }

    @GET
    @Path("hello")
    @Produces(MediaType.APPLICATION_JSON)
    public String hello() {
        return "Hello";
    }
}
