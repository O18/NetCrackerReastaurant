package forms.selection;

import client.MenuClient;
import forms.creation.controller.CreationController;
import forms.creation.view.CreationPresenter;
import forms.creation.view.MenuCreationScreen;
import forms.eventbus.EventBus;
import forms.viwer.view.MenuViewerScreen;
import forms.selection.controller.SelectionController;
import forms.selection.view.MenuSelectionScreen;
import forms.selection.view.SelectionPresenter;

import javax.swing.*;

/**
 * Created by User on 29.01.2017
 */
public class Main {

    public static void main(String[] args) {
        MenuClient client = new MenuClient();

        MenuViewerScreen viewScreen = new MenuViewerScreen();
        MenuCreationScreen creationScreen = new MenuCreationScreen(viewScreen);

        EventBus selectionScreenEventBus = new EventBus();
        SelectionController selectionController = new SelectionController(selectionScreenEventBus, client);
        MenuSelectionScreen selectionScreen = new MenuSelectionScreen(viewScreen, creationScreen);
        SelectionPresenter selectionPresenter = new SelectionPresenter(selectionScreenEventBus, selectionScreen);
        selectionScreen.setPresenter(selectionPresenter);
        selectionScreen.updateMenusList();

        viewScreen.setSelectionScreen(selectionScreen);

        EventBus creationEventBus = new EventBus();
        CreationController creationController = new CreationController(creationEventBus, client);
        CreationPresenter creationPresenter = new CreationPresenter(creationEventBus, creationScreen);
        creationScreen.setPresenter(creationPresenter);

        selectionScreen.setLocationRelativeTo(null);
        selectionScreen.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        creationScreen.setLocationRelativeTo(null);
        creationScreen.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        viewScreen.setLocationRelativeTo(null);
        viewScreen.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        selectionScreen.setVisible(true);
    }
}
