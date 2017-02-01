package forms.viwer.events;

import forms.eventbus.Event;
import model.DishDTO;

/**
 * Created by User on 30.01.2017
 */
public class ChangeDishEvent implements Event {
    private final String menuName;
    private final DishDTO dish;
    private final String oldDishName;
    private final ViewerCallback callback;
    private final String categoryName;


    public ChangeDishEvent(String menuName, DishDTO dish, String oldDishName, String categoryName, ViewerCallback callback) {
        this.menuName = menuName;
        this.dish = dish;
        this.oldDishName = oldDishName;
        this.callback = callback;
        this.categoryName = categoryName;
    }

    public String getMenuName() {
        return menuName;
    }

    public DishDTO getDish() {
        return dish;
    }

    public String getOldDishName() {
        return oldDishName;
    }

    public ViewerCallback getCallback() {
        return callback;
    }

    public String getCategoryName() {
        return categoryName;
    }
}
