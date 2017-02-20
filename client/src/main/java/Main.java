import client.MenuClient;
import forms.eventbus.EventBus;
import forms.selection.MenuSelectionScreen;
import forms.selection.SelectionController;
import forms.selection.SelectionPresenter;
import forms.viwer.ViewerController;
import forms.viwer.MenuViewerScreen;
import forms.viwer.ViewerPresenter;

import javax.swing.*;

/**
 * Created by User on 29.01.2017
 */
public class Main {

    public static void main(String[] args) {
        MenuClient client = new MenuClient();

        MenuViewerScreen viewScreen = new MenuViewerScreen();
        MenuSelectionScreen selectionScreen = new MenuSelectionScreen(viewScreen);

        EventBus selectionScreenEventBus = new EventBus();
        SelectionController selectionController = new SelectionController(selectionScreenEventBus, client);
        SelectionPresenter selectionPresenter = new SelectionPresenter(selectionScreenEventBus, selectionScreen);
        selectionScreen.setPresenter(selectionPresenter);
        selectionScreen.updateMenusList();

        EventBus viewerEventBus = new EventBus();
        ViewerController viewerController = new ViewerController(viewerEventBus, client);
        ViewerPresenter viewerPresenter = new ViewerPresenter(viewScreen, viewerEventBus);
        viewScreen.setPresenter(viewerPresenter);
        viewScreen.setSelectionScreen(selectionScreen);

        selectionScreen.setLocationRelativeTo(null);
        selectionScreen.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        viewScreen.setLocationRelativeTo(null);
        viewScreen.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        selectionScreen.setVisible(true);
    }
}
