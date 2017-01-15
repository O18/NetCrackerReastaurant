package com.o18.restaurant;


import com.o18.restaurant.model.Menu;
import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

/**
 * Created by Александр on 15.01.2017.
 */
public class MenuClient {

    public static void main(String[] args) {
        Client client = ClientBuilder.newClient().register(JacksonFeature.class);
        WebTarget target = client.target("http://localhost:2222").path("menus/get");

        Menu menu = target.request(MediaType.APPLICATION_JSON_TYPE).get(Menu.class);
        System.out.println(menu);
    }
}
