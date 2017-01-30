package forms.viwer.events;

import forms.eventbus.Event;
import model.CategoryDTO;

/**
 * Created by User on 30.01.2017
 */
public class ChangeCategoryEvent implements Event {
    private final String menuName;
    private final CategoryDTO category;
    private final String oldCategoryName;
    private final ViewerCallback callback;

    public ChangeCategoryEvent(String menuName, CategoryDTO category, String oldCategoryName, ViewerCallback callback) {
        this.menuName = menuName;
        this.category = category;
        this.oldCategoryName = oldCategoryName;
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

    public String getOldCategoryName() {
        return oldCategoryName;
    }
}
