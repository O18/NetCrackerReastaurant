package forms.viwer.view;

import forms.eventbus.EventBus;
import forms.viwer.events.*;
import model.CategoryDTO;
import model.DishDTO;
import model.MenuDTO;

/**
 * Created by User on 30.01.2017
 */
public class ViewerPresenter {

    private final MenuViewerScreen viewerScreen;
    private final EventBus eventBus;
    private final ViewerCallback callback;

    public ViewerPresenter(MenuViewerScreen viewerScreen, EventBus eventBus) {
        this.viewerScreen = viewerScreen;
        this.eventBus = eventBus;
        callback = new ViewerCallback() {
            @Override
            public void onSuccess(MenuDTO menu) {
                viewerScreen.updateMenu(menu);
            }

            @Override
            public void onFail(RuntimeException e) {
                viewerScreen.showErrorMessage(e.getMessage());
            }
        };
    }

    void addCategory(CategoryDTO categoryDTO, String menuName){
        eventBus.post(new AddCategoryEvent(menuName, categoryDTO, callback));
    }

    void changeCategory(String newCategoryName, String oldCategoryName, String menuName){
        eventBus.post(new ChangeCategoryEvent(menuName, newCategoryName, oldCategoryName, callback));
    }

    void deleteCategoryEvent(String deletedCategoryName, String menuName){
        eventBus.post(new DeleteCategoryEvent(menuName, deletedCategoryName, callback));
    }
    void addDish(String menuName, DishDTO dish, String categoryName){
        eventBus.post(new AddDishEvent(menuName,dish,categoryName,callback));
    }
    void deleteDish(String deletedDishName, String menuName,String categoryName) {
        eventBus.post(new DeleteDishEvent(menuName,deletedDishName, categoryName, callback));
    }
    void changeDish(DishDTO dish, String oldDishName, String menuName,String categoryName) {
        eventBus.post(new ChangeDishEvent(menuName,dish,oldDishName, categoryName, callback));
    }
}