package forms.viwer;

import forms.eventbus.Event;

/**
 * Created by User on 30.01.2017
 */
class DeleteCategoryEvent implements Event{
    private final String menuName;
    private final String deleteCategoryName;
    private final ViewerCallback callback;

    DeleteCategoryEvent(String menuName, String deleteCategoryName, ViewerCallback callback) {
        this.menuName = menuName;
        this.deleteCategoryName = deleteCategoryName;
        this.callback = callback;
    }

    ViewerCallback getCallback() {
        return callback;
    }

    String getMenuName() {
        return menuName;
    }

    String getDeleteCategoryName() {
        return deleteCategoryName;
    }
}
