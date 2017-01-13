package com.o18.restaurant.server;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by Александр on 13.01.2017.
 */
@Path("menus")
public class MenuRestService {

    @GET
    @Path("get")
    @Produces(MediaType.APPLICATION_JSON)
    public Object getMenus() {
        return new Object();
    }

    @GET
    @Path("hello")
    @Produces(MediaType.APPLICATION_JSON)
    public String hello() {
        return "Hello";
    }
}
