package forms.selection;

import forms.eventbus.Event;

/**
 * Created by User on 29.01.2017
 */
class GetMenuEvent implements Event {
    private final String menuName;
    private final GetMenuCallback callback;

    GetMenuEvent(String menuName, GetMenuCallback callback) {
        this.menuName = menuName;
        this.callback = callback;
    }

    String getMenuName() {
        return menuName;
    }

    GetMenuCallback getCallback() {
        return callback;
    }
}
