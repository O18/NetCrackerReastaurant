package forms.viwer.controller;

import client.MenuClient;
import forms.eventbus.EventBus;
import forms.viwer.events.*;
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
        eventBus.addHandler(AddDishEvent.class,this::addDish);
        eventBus.addHandler(ChangeDishEvent.class,this::changeDish);
        eventBus.addHandler(DeleteDishEvent.class,this::deleteDish);
    }

    private void addCategory(AddCategoryEvent event){
        try{
            client.addCategory(event.getCategory(), event.getMenuName());
            MenuDTO changedMenu = client.getMenu(event.getMenuName());
            event.getCallback().onSuccess(changedMenu);
        }catch (RuntimeException e){
            event.getCallback().onFail(e);
        }
    }

    private void changeCategory(ChangeCategoryEvent event){
        try{
            client.changeCategory(event.getNewCategoryName(), event.getOldCategoryName(), event.getMenuName());
            MenuDTO changedMenu = client.getMenu(event.getMenuName());
            event.getCallback().onSuccess(changedMenu);
        }catch (RuntimeException e){
            event.getCallback().onFail(e);
        }
    }

    private void deleteCategory(DeleteCategoryEvent event){
        try{
            client.deleteCategory(event.getDeleteCategoryName(), event.getMenuName());
            MenuDTO changedMenu = client.getMenu(event.getMenuName());
            event.getCallback().onSuccess(changedMenu);
        }catch (RuntimeException e){
            event.getCallback().onFail(e);
        }
    }
    private void addDish(AddDishEvent event) {
        try{
            client.addDish(event.getDish(),event.getCategoryName(),event.getMenuName());
            MenuDTO changedMenu = client.getMenu(event.getMenuName());
            event.getCallback().onSuccess(changedMenu);
        }catch (RuntimeException e){
            event.getCallback().onFail(e);
        }
    }
    private void changeDish(ChangeDishEvent event) {
        try{
            client.changeDish(event.getDish(),event.getOldDishName(),event.getCategoryName(),event.getMenuName());
            MenuDTO changedMenu = client.getMenu(event.getMenuName());
            event.getCallback().onSuccess(changedMenu);
        }catch (RuntimeException e) {
            event.getCallback().onFail(e);
        }
    }
    private void deleteDish(DeleteDishEvent event) {
        try{
            client.deleteDish(event.getDeleteDishName(),event.getCategoryName(),event.getMenuName());
            MenuDTO changedMenu = client.getMenu(event.getMenuName());
            event.getCallback().onSuccess(changedMenu);
        }catch (RuntimeException e) {
            event.getCallback().onFail(e);
        }
    }
}
