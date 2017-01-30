package forms.viwer.events;

import forms.eventbus.Callback;
import model.MenuDTO;

/**
 * Created by User on 30.01.2017
 */
public interface ViewerCallback extends Callback{

    void onSuccess(MenuDTO menu);
}
