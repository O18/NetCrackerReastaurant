package forms.selection.view;

import forms.eventbus.EventBus;
import forms.selection.events.GetMenuCallback;
import forms.selection.events.GetMenuEvent;
import forms.selection.events.GetMenuNamesCallback;
import forms.selection.events.GetMenuNamesEvent;
import model.MenuDTO;

import java.util.*;

/**
 * Created by User on 29.01.2017
 */
public class SelectionPresenter {
    private final EventBus eventBus;
    private final MenuSelectionScreen selectionScreen;

    public SelectionPresenter(EventBus eventBus, MenuSelectionScreen selectionScreen){
        this.eventBus = eventBus;
        this.selectionScreen = selectionScreen;
    }

    void getMenuNamesList(){
        eventBus.post(new GetMenuNamesEvent(new GetMenuNamesCallback() {
            @Override
            public void onSuccess(Set<String> menuNames) {
                List<String> menusNamesList = new ArrayList<>();
                menusNamesList.addAll(menuNames);
                menusNamesList.sort(String::compareTo);
                selectionScreen.setMenuList(menusNamesList);
            }

            @Override
            public void onFail(RuntimeException e) {

            }
        }));
    }

    void getMenuByName(String menuName){
        eventBus.post(new GetMenuEvent(menuName, new GetMenuCallback() {
            @Override
            public void onSuccess(MenuDTO menuDTO, String menuName) {
                selectionScreen.openMenu(menuDTO, menuName);
            }

            @Override
            public void onFail(RuntimeException e) {

            }
        }));
    }
}