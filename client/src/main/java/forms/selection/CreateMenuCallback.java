package forms.selection;

import forms.eventbus.Callback;
import model.MenuDTO;

/**
 * Created by User on 30.01.2017
 */
abstract class CreateMenuCallback implements Callback{

    abstract void onSuccess(MenuDTO menu, String menuName);
}
