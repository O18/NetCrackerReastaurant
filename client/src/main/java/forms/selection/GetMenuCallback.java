package forms.selection;

import forms.eventbus.Callback;
import model.MenuDTO;

/**
 * Created by User on 29.01.2017
 */
public abstract class GetMenuCallback implements Callback {

    public abstract void onSuccess(MenuDTO menuDTO, String menuName);
}
