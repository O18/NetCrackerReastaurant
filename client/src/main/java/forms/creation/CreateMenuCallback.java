package forms.creation;

import forms.eventbus.Callback;
import model.MenuDTO;

/**
 * Created by User on 30.01.2017
 */
public abstract class CreateMenuCallback implements Callback{

    public abstract void onSuccess(MenuDTO menu, String menuName);
}