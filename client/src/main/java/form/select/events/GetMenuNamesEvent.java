package form.select.events;

import form.eventbus.Callback;
import form.eventbus.Event;

/**
 * Created by User on 29.01.2017
 */
public class GetMenuNamesEvent implements Event {
    private final Callback callback;

    public GetMenuNamesEvent(Callback callback) {
        this.callback = callback;
    }

    public Callback getCallback() {
        return callback;
    }
}
