package forms.viwer.events;

import forms.eventbus.Event;
import model.CategoryDTO;

/**
 * Created by User on 30.01.2017
 */
public class AddCategoryEvent implements Event {
    private final CategoryDTO category;
    private final ViewerCallback callback;

    public AddCategoryEvent(CategoryDTO category, ViewerCallback callback) {
        this.category = category;
        this.callback = callback;
    }
}
