package forms.viwer;

import forms.eventbus.Event;
import model.DishDTO;

/**
 * Created by User on 30.01.2017
 */
class ChangeDishEvent implements Event {
    private final String menuName;
    private final DishDTO dish;
    private final String oldDishName;
    private final ViewerCallback callback;
    private final String categoryName;


    ChangeDishEvent(String menuName, DishDTO dish, String oldDishName, String categoryName, ViewerCallback callback) {
        this.menuName = menuName;
        this.dish = dish;
        this.oldDishName = oldDishName;
        this.callback = callback;
        this.categoryName = categoryName;
    }

    String getMenuName() {
        return menuName;
    }

    DishDTO getDish() {
        return dish;
    }

    String getOldDishName() {
        return oldDishName;
    }

    ViewerCallback getCallback() {
        return callback;
    }

    String getCategoryName() {
        return categoryName;
    }
}
