package forms.creation.events;

import forms.eventbus.Event;

/**
 * Created by User on 30.01.2017
 */
public class CreateMenuEvent implements Event{
    private final String menuName;
    private final CreateMenuCallback callback;

    public CreateMenuEvent(String menuName, CreateMenuCallback callback) {
        this.menuName = menuName;
        this.callback = callback;
    }

    public String getMenuName() {
        return menuName;
    }

    public CreateMenuCallback getCallback() {
        return callback;
    }
}
