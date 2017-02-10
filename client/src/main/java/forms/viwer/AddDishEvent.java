package forms.viwer;

import forms.eventbus.Event;
import model.DishDTO;

/**
 * Created by User on 30.01.2017
 */
public class AddDishEvent implements Event {
    private final String menuName;
    private final DishDTO dish;
    private final ViewerCallback callback;
    private final String categoryName;


    public AddDishEvent(String menuName, DishDTO dish, String categoryName, ViewerCallback callback) {
        this.menuName = menuName;
        this.dish = dish;
        this.callback = callback;
        this.categoryName = categoryName;

    }

    public String getMenuName() {
        return menuName;
    }

    public DishDTO getDish() {
        return dish;
    }

    public ViewerCallback getCallback() {
        return callback;
    }

    public String getCategoryName() {
        return categoryName;
    }
}
