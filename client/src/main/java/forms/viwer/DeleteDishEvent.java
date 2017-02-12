package forms.viwer;

import forms.eventbus.Event;

/**
 * Created by User on 30.01.2017
 */
class DeleteDishEvent implements Event {
    private final String menuName;
    private final String deleteDishName;
    private final ViewerCallback callback;
    private final String categoryName;

    DeleteDishEvent(String menuName, String deleteDishName, String categoryName, ViewerCallback callback) {
        this.menuName = menuName;
        this.deleteDishName = deleteDishName;
        this.callback = callback;
        this.categoryName = categoryName;
    }

    String getMenuName() {
        return menuName;
    }

    String getDeleteDishName() {
        return deleteDishName;
    }

    ViewerCallback getCallback() {
        return callback;
    }

    String getCategoryName() {
        return categoryName;
    }
}
