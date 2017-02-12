package forms.selection;

import forms.eventbus.Event;

/**
 * Created by User on 06.02.2017
 */
class DeleteMenuEvent implements Event {
    private final String menuName;
    private final DeleteMenuCallback callback;

    DeleteMenuEvent(String menuName, DeleteMenuCallback callback) {
        this.menuName = menuName;
        this.callback = callback;
    }

    String getMenuName() {
        return menuName;
    }

    DeleteMenuCallback getCallback() {
        return callback;
    }
}
