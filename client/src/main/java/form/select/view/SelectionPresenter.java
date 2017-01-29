package form.select.view;

import form.eventbus.Callback;
import form.eventbus.EventBus;
import form.select.events.GetMenuNamesEvent;

import java.util.List;

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

    List<String> getMenuNamesList(){
        eventBus.post(new GetMenuNamesEvent(new Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onFail(RuntimeException e) {

            }
        }));
    }
}
