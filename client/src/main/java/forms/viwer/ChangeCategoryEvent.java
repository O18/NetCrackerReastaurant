package forms.viwer;

import forms.eventbus.Event;

/**
 * Created by User on 30.01.2017
 */
class ChangeCategoryEvent implements Event {
    private final String menuName;
    private final String newCategoryName;
    private final String oldCategoryName;
    private final ViewerCallback callback;

    ChangeCategoryEvent(String menuName, String newCategoryName, String oldCategoryName, ViewerCallback callback) {
        this.menuName = menuName;
        this.newCategoryName = newCategoryName;
        this.oldCategoryName = oldCategoryName;
        this.callback = callback;
    }

    String getNewCategoryName() {
        return newCategoryName;
    }

    ViewerCallback getCallback() {
        return callback;
    }

    String getMenuName() {
        return menuName;
    }

    String getOldCategoryName() {
        return oldCategoryName;
    }
}
