package forms.selection;

import forms.eventbus.Event;

/**
 * Created by User on 29.01.2017
 */
class GetMenuNamesEvent implements Event {
    private final GetMenuNamesCallback callback;

    GetMenuNamesEvent(GetMenuNamesCallback callback) {
        this.callback = callback;
    }

    GetMenuNamesCallback getCallback() {
        return callback;
    }
}
