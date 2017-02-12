package forms.viwer;

import forms.eventbus.Event;
import model.CategoryDTO;

/**
 * Created by User on 30.01.2017
 */
class AddCategoryEvent implements Event {
    private final String menuName;
    private final CategoryDTO category;
    private final ViewerCallback callback;

    AddCategoryEvent(String menuName, CategoryDTO category, ViewerCallback callback) {
        this.menuName = menuName;
        this.category = category;
        this.callback = callback;
    }

    CategoryDTO getCategory() {
        return category;
    }

    ViewerCallback getCallback() {
        return callback;
    }

    String getMenuName() {
        return menuName;
    }
}
