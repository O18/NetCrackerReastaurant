package forms.viwer.events;

import forms.eventbus.Event;

/**
 * Created by User on 30.01.2017
 */
public class DeleteCategoryEvent implements Event{
    private final String menuName;
    private final String deleteCategoryName;
    private final ViewerCallback callback;

    public DeleteCategoryEvent(String menuName, String deleteCategoryName, ViewerCallback callback) {
        this.menuName = menuName;
        this.deleteCategoryName = deleteCategoryName;
        this.callback = callback;
    }

    public ViewerCallback getCallback() {
        return callback;
    }

    public String getMenuName() {
        return menuName;
    }

    public String getDeleteCategoryName() {
        return deleteCategoryName;
    }
}
