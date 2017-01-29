package form.select.controller;

import client.MenuClient;
import form.eventbus.EventBus;
import form.select.events.GetMenuNamesEvent;

import java.util.Set;

/**
 * Created by User on 29.01.2017
 */
public class SelectionController {
    private final MenuClient client;

    public SelectionController(EventBus eventBus, MenuClient client) {
        this.client = client;

        eventBus.addHandler(GetMenuNamesEvent.class, this::loadMenuNames);
    }

    private void loadMenuNames(GetMenuNamesEvent event){
        try {
            Set<String> menuNames = client.getMenusNames();
            event.getCallback().onSuccess(menuNames);
        } catch (RuntimeException e){
            event.getCallback().onFail(e);
        }
    }


}
