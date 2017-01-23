package com.o18.restaurant.server;

import com.o18.restaurant.model.Menu;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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
    public Response loadMenu(@PathParam("menu_name") String menuName){
        try {
            return Response.status(Response.Status.OK).entity(data.getMenuWithName(menuName)).build() ;
        }catch (RuntimeException e){
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }

    }

    @POST
    @Path("{menu_name}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response saveMenu(Menu menu, @PathParam("menu_name") String menuName){
        try {
            data.saveMenu(menu, menuName);
            return Response.status(Response.Status.OK).build();
        }catch (RuntimeException e){
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }

    }

    @DELETE
    @Path("{menu_name}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteMenu(@PathParam("{menu_name}") String menuName){
        if(data.deleteMenu(menuName)){
            return Response.status(Response.Status.OK).build();
        }else {
            return Response.status(Response.Status.NOT_FOUND).entity("Не удалось удалить").build();
        }
    }
}
