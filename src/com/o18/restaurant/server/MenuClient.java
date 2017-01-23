package com.o18.restaurant.server;

import com.o18.restaurant.model.Category;
import com.o18.restaurant.model.Menu;
import org.glassfish.jersey.client.ClientResponse;
import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Set;
import java.util.StringJoiner;

/**
 * Created by User on 23.01.2017.
 */
public class MenuClient {
    private WebTarget target;

    public MenuClient(){
        Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();
        target = client.target("http://localhost:2222").path("menus");
    }

    public Set<String> getMenusNames(){
        @SuppressWarnings("unchecked")
        Set<String> menusNames = target.request(MediaType.APPLICATION_JSON_TYPE).get(Set.class);

        return menusNames;
    }

    public Menu loadMenu(String menuFileName){
        Response response = target.path(menuFileName).request(MediaType.APPLICATION_JSON_TYPE).get();
        if(response.getStatus() == Response.Status.OK.getStatusCode()){
            return response.readEntity(Menu.class);
        }else {
            throw new RuntimeException( response.readEntity(String.class));
        }
    }

    public void deleteMenu(String menuFileName){
        target.path(menuFileName).request().delete();
    }

    public void saveMenu(String menuFileName, Menu menu){
        Response response = target.path(menuFileName).request(MediaType.APPLICATION_JSON_TYPE).post(Entity.entity(menu, MediaType.APPLICATION_JSON_TYPE));
        if(response.getStatus() != Response.Status.OK.getStatusCode()){
            System.err.println(response);
            //throw new RuntimeException(response.readEntity(String.class));
        }
    }

    public static void main(String[] args) {
        MenuClient client = new MenuClient();
        Menu menu = new Menu();
        menu.addCategory(new Category("Мясо"));
        client.saveMenu("menu.menu", menu);
        Menu menu1 = client.loadMenu("menu.menu");
        System.out.println(menu1);
    }
}
