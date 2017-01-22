package com.o18.restaurant.server;

import com.o18.restaurant.model.Menu;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Set;

/**
 * Created by Александр on 13.01.2017.
 */

@Path("menus")
public class MenuRestService {
    @Singleton
    private final ServerData data = new ServerData();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Set<String> getMenusList(){
        return data.getNamesOfMenus();
    }

    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{menu_name}")
    public Menu loadMenu(@PathParam("menu_name") String menuName){
        return data.getMenuWithName(menuName);
    }

    @POST
    @Path("{menu_name}")
    public void saveMenu(@QueryParam("{menu}") Menu menu, @PathParam("menu_name") String menuName){
        data.saveMenu(menu, menuName);
    }

    @DELETE
    @Path("{menu_name}")
    public void deleteMenu(@PathParam("{menu_name}") String menuName){
        data.deleteMenu(menuName);
    }
}
