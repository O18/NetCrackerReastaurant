package forms.selection.events;

import forms.eventbus.Event;

/**
 * Created by User on 06.02.2017
 */
public class DeleteMenuEvent implements Event {
    private final String menuName;
    private final DeleteMenuCallback callback;

    public DeleteMenuEvent(String menuName, DeleteMenuCallback callback) {
        this.menuName = menuName;
        this.callback = callback;
    }

    public String getMenuName() {
        return menuName;
    }

    public DeleteMenuCallback getCallback() {
        return callback;
    }
}
