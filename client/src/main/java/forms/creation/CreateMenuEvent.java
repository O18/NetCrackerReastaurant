package forms.creation;

import forms.eventbus.Event;

/**
 * Created by User on 30.01.2017
 */
class CreateMenuEvent implements Event{
    private final String menuName;
    private final CreateMenuCallback callback;

    CreateMenuEvent(String menuName, CreateMenuCallback callback) {
        this.menuName = menuName;
        this.callback = callback;
    }

    String getMenuName() {
        return menuName;
    }

    CreateMenuCallback getCallback() {
        return callback;
    }
}
