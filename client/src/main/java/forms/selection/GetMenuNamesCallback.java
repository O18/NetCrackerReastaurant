package forms.selection;

import forms.eventbus.Callback;

import java.util.Set;

/**
 * Created by User on 29.01.2017
 */
abstract class GetMenuNamesCallback implements Callback{

    abstract void onSuccess(Set<String> menuNames);
}
