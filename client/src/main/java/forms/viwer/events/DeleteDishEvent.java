package forms.viwer.events;

import forms.eventbus.Event;

/**
 * Created by User on 30.01.2017
 */
public class DeleteDishEvent implements Event {
    private final String menuName;
    private final String deleteDishName;
    private final ViewerCallback callback;
    private final String categoryName;

    public DeleteDishEvent(String menuName, String deleteDishName, String categoryName, ViewerCallback callback) {
        this.menuName = menuName;
        this.deleteDishName = deleteDishName;
        this.callback = callback;
        this.categoryName = categoryName;
    }

    public String getMenuName() {
        return menuName;
    }

    public String getDeleteDishName() {
        return deleteDishName;
    }

    public ViewerCallback getCallback() {
        return callback;
    }

    public String getCategoryName() {
        return categoryName;
    }
}
