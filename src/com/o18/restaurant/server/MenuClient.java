package com.o18.restaurant.server;

import com.o18.restaurant.model.Menu;
import org.glassfish.jersey.client.ClientResponse;
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
    private WebTarget target;

    public MenuClient(){
        Client client = ClientBuilder.newClient().register(JacksonFeature.class);
        target = client.target("http://localhost:2222").path("menus");
    }

    public Set<String> getMenusNames(){
        @SuppressWarnings("unchecked")
        Set<String> menusNames = target.request(MediaType.APPLICATION_JSON_TYPE).get(Set.class);

        return menusNames;
    }

    public Menu loadMenu(String menuFileName){
        return target.path(menuFileName).request(MediaType.APPLICATION_JSON_TYPE).get(Menu.class);
    }

    public void deleteMenu(String menuFileName){
        target.path(menuFileName).request().delete();
    }

    public void saveMenu(String menuFileName, Menu menu){
        target.path(menuFileName).request().post(Entity.entity(menu, MediaType.APPLICATION_JSON_TYPE), ClientResponse.class);
    }

    public static void main(String[] args) {
        MenuClient client = new MenuClient();
        Menu menu = new Menu();
        System.out.println(client.loadMenu("menu.menu"));
    }
}
