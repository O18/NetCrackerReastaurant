package form.select.events;

import java.util.Set;

/**
 * Created by User on 29.01.2017
 */
public abstract class GetMenuNamesCallback {

    public abstract void onSuccess(Set<String> menuNames);

    public abstract void onFail(RuntimeException e);
}
