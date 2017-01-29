package forms.viwer.view;

import forms.eventbus.EventBus;

/**
 * Created by User on 30.01.2017
 */
public class ViewerPresenter {

    private final MenuViewerScreen viewerScreen;
    private final EventBus eventBus;

    public ViewerPresenter(MenuViewerScreen viewerScreen, EventBus eventBus) {
        this.viewerScreen = viewerScreen;
        this.eventBus = eventBus;
    }


}
