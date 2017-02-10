package forms.selection;

import forms.eventbus.Event;

/**
 * Created by User on 29.01.2017
 */
public class GetMenuEvent implements Event {
    private final String menuName;
    private final GetMenuCallback callback;

    public GetMenuEvent(String menuName, GetMenuCallback callback) {
        this.menuName = menuName;
        this.callback = callback;
    }

    public String getMenuName() {
        return menuName;
    }

    public GetMenuCallback getCallback() {
        return callback;
    }
}
