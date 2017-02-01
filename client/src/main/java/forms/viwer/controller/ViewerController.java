package forms.viwer.controller;

import client.MenuClient;
import forms.eventbus.EventBus;
import forms.viwer.events.AddCategoryEvent;
import forms.viwer.events.ChangeCategoryEvent;
import forms.viwer.events.DeleteCategoryEvent;
import model.MenuDTO;

/**
 * Created by User on 30.01.2017
 */
public class ViewerController {
    private final MenuClient client;

    public ViewerController(EventBus eventBus, MenuClient client) {
        this.client = client;

        eventBus.addHandler(AddCategoryEvent.class, this::addCategory);
        eventBus.addHandler(ChangeCategoryEvent.class, this::changeCategory);
        eventBus.addHandler(DeleteCategoryEvent.class, this::deleteCategory);
    }

    private void addCategory(AddCategoryEvent event){
        try{
            client.addCategory(event.getCategory(), event.getMenuName());
            MenuDTO changedMenu = client.getMenu(event.getMenuName());
            event.getCallback().onSuccess(changedMenu, event.getMenuName());
        }catch (RuntimeException e){
            event.getCallback().onFail(e);
        }
    }

    private void changeCategory(ChangeCategoryEvent event){
        try{
            client.changeCategory(event.getCategory(), event.getOldCategoryName(), event.getMenuName());
            MenuDTO changedMenu = client.getMenu(event.getMenuName());
            event.getCallback().onSuccess(changedMenu, event.getMenuName());
        }catch (RuntimeException e){
            event.getCallback().onFail(e);
        }
    }

    private void deleteCategory(DeleteCategoryEvent event){
        try{
            client.deleteCategory(event.getDeleteCategoryName(), event.getMenuName());
            MenuDTO changedMenu = client.getMenu(event.getMenuName());
            event.getCallback().onSuccess(changedMenu, event.getMenuName());
        }catch (RuntimeException e){
            event.getCallback().onFail(e);
        }
    }
}
