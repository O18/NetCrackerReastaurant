package forms.viwer.controller;

import client.MenuClient;
import forms.eventbus.EventBus;

/**
 * Created by User on 30.01.2017
 */
public class ViewerController {
    private final MenuClient client;

    public ViewerController(EventBus eventBus, MenuClient client) {
        this.client = client;

        eventBus.addHandler();
    }

}
