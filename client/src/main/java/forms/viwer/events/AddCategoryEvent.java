package forms.viwer.events;

import forms.eventbus.Event;
import model.CategoryDTO;

/**
 * Created by User on 30.01.2017
 */
public class AddCategoryEvent implements Event {
    private final String menuName;
    private final CategoryDTO category;
    private final ViewerCallback callback;

    public AddCategoryEvent(String menuName, CategoryDTO category, ViewerCallback callback) {
        this.menuName = menuName;
        this.category = category;
        this.callback = callback;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public ViewerCallback getCallback() {
        return callback;
    }

    public String getMenuName() {
        return menuName;
    }
}
