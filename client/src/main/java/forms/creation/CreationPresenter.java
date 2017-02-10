package forms.creation;

import forms.eventbus.EventBus;
import model.MenuDTO;

/**
 * Created by User on 30.01.2017
 */
public class CreationPresenter {

    private final EventBus eventBus;
    private final MenuCreationScreen creationScreen;

    public CreationPresenter(EventBus eventBus, MenuCreationScreen creationScreen) {
        this.eventBus = eventBus;
        this.creationScreen = creationScreen;
    }

    void createMenu(String menuName) {
        eventBus.post(new CreateMenuEvent(menuName, new CreateMenuCallback() {
            @Override
            public void onFail(RuntimeException e) {
                creationScreen.showErrorMessage(e.getMessage());
            }

            @Override
            public void onSuccess(MenuDTO menu, String menuName) {
                creationScreen.openMenu(menu, menuName);
            }
        }));
    }
}
