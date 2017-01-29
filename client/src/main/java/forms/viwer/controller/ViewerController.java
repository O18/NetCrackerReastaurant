package forms.viwer.controller;

import client.MenuClient;
import forms.eventbus.EventBus;
import forms.viwer.events.AddCategoryEvent;

/**
 * Created by User on 30.01.2017
 */
public class ViewerController {
    private final MenuClient client;

    public ViewerController(EventBus eventBus, MenuClient client) {
        this.client = client;

        eventBus.addHandler(AddCategoryEvent.class, this::addCategory);
        //todo добавить все эвенты
    }

    private void addCategory(AddCategoryEvent event){//todo

    }
}
