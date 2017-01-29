package form.select.view;

import form.eventbus.Callback;
import form.eventbus.EventBus;
import form.select.events.GetMenuNamesCallback;
import form.select.events.GetMenuNamesEvent;

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
}
