package com.o18.restaurant;


import com.o18.restaurant.model.Menu;
import com.o18.restaurant.server.MenuRestService;
import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.util.Set;

/**
 * Created by Александр on 15.01.2017.
 */
public class MenuClient {

    public static void main(String[] args) {
        Client client = ClientBuilder.newClient().register(JacksonFeature.class).register(new MenuRestService());
        WebTarget menusTarget = client.target("http://localhost:2222").path("menus");

        @SuppressWarnings("unchecked")
        Set<String> listMenu = menusTarget.queryParam("path", "menus").request(MediaType.APPLICATION_JSON_TYPE).get(Set.class);

        System.out.println(listMenu);

    }
}
