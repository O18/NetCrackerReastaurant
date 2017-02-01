package forms.viwer.view;

import forms.eventbus.EventBus;
import forms.viwer.events.AddCategoryEvent;
import forms.viwer.events.ChangeCategoryEvent;
import forms.viwer.events.DeleteCategoryEvent;
import forms.viwer.events.ViewerCallback;
import model.CategoryDTO;
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
            public void onSuccess(MenuDTO menu, String menuName) {
                viewerScreen.setCurrentMenu(menu, menuName);
            }

            @Override
            public void onFail(RuntimeException e) {

            }
        };
    }

    void addCategory(CategoryDTO categoryDTO, String menuName){
        eventBus.post(new AddCategoryEvent(menuName, categoryDTO, callback));
    }

    void changeCategory(CategoryDTO categoryDTO, String oldCategoryName, String menuName){
        eventBus.post(new ChangeCategoryEvent(menuName, categoryDTO, oldCategoryName, callback));
    }

    void deleteCategoryEvent(String deletedCategoryName, String menuName){
        eventBus.post(new DeleteCategoryEvent(menuName, deletedCategoryName, callback));
    }
}