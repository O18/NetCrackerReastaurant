package forms.viwer;

import forms.eventbus.Event;
import model.DishDTO;

/**
 * Created by User on 30.01.2017
 */
class AddDishEvent implements Event {
    private final String menuName;
    private final DishDTO dish;
    private final ViewerCallback callback;
    private final String categoryName;


    AddDishEvent(String menuName, DishDTO dish, String categoryName, ViewerCallback callback) {
        this.menuName = menuName;
        this.dish = dish;
        this.callback = callback;
        this.categoryName = categoryName;

    }

    String getMenuName() {
        return menuName;
    }

    DishDTO getDish() {
        return dish;
    }

    ViewerCallback getCallback() {
        return callback;
    }

    String getCategoryName() {
        return categoryName;
    }
}
