package forms.creation.controller;

import client.MenuClient;
import forms.creation.events.CreateMenuEvent;
import forms.eventbus.EventBus;
import model.MenuDTO;

/**
 * Created by User on 30.01.2017
 */
public class CreationController {
    private final MenuClient client;

    public CreationController(EventBus eventBus, MenuClient client) {
        this.client = client;

        eventBus.addHandler(CreateMenuEvent.class, this::createMenu);
    }

    private void createMenu(CreateMenuEvent event){
        try{
            client.createMenu(event.getMenuName());
            MenuDTO createdMenu = client.getMenu(event.getMenuName());
            event.getCallback().onSuccess(createdMenu, event.getMenuName());
        } catch (RuntimeException e){
            event.getCallback().onFail(e);
        }
    }
}