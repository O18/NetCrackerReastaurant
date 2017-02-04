package forms.viwer.events;

import forms.eventbus.Event;
import model.CategoryDTO;

/**
 * Created by User on 30.01.2017
 */
public class ChangeCategoryEvent implements Event {
    private final String menuName;
    private final String newCategoryName;
    private final String oldCategoryName;
    private final ViewerCallback callback;

    public ChangeCategoryEvent(String menuName, String newCategoryName, String oldCategoryName, ViewerCallback callback) {
        this.menuName = menuName;
        this.newCategoryName = newCategoryName;
        this.oldCategoryName = oldCategoryName;
        this.callback = callback;
    }

    public String getNewCategoryName() {
        return newCategoryName;
    }

    public ViewerCallback getCallback() {
        return callback;
    }

    public String getMenuName() {
        return menuName;
    }

    public String getOldCategoryName() {
        return oldCategoryName;
    }
}
