package forms.selection;

import forms.eventbus.Event;

/**
 * Created by User on 29.01.2017
 */
public class GetMenuNamesEvent implements Event {
    private final GetMenuNamesCallback callback;

    public GetMenuNamesEvent(GetMenuNamesCallback callback) {
        this.callback = callback;
    }

    public GetMenuNamesCallback getCallback() {
        return callback;
    }
}
