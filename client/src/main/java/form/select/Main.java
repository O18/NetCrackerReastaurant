package form.select;

import client.MenuClient;
import form.create.MenuCreateScreen;
import form.eventbus.EventBus;
import form.main.MenuViewScreen;
import form.select.controller.SelectionController;
import form.select.view.MenuSelectionScreen;
import form.select.view.SelectionPresenter;

import javax.swing.*;

/**
 * Created by User on 29.01.2017
 */
public class Main {

    public static void main(String[] args) {
        MenuClient client = new MenuClient();

        MenuViewScreen viewScreen = new MenuViewScreen();
        MenuCreateScreen createScreen = new MenuCreateScreen(viewScreen);

        EventBus selectionScreenEventBus = new EventBus();
        SelectionController selectionController = new SelectionController(selectionScreenEventBus, client);
        MenuSelectionScreen selectionScreen = new MenuSelectionScreen(viewScreen, createScreen);
        SelectionPresenter selectionPresenter = new SelectionPresenter(selectionScreenEventBus, selectionScreen);
        selectionScreen.setPresenter(selectionPresenter);
        selectionScreen.updateMenusList();

        viewScreen.setSelectionScreen(selectionScreen);

        selectionScreen.setLocationRelativeTo(null);
        selectionScreen.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        createScreen.setLocationRelativeTo(null);
        createScreen.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        viewScreen.setLocationRelativeTo(null);
        viewScreen.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        selectionScreen.setVisible(true);
    }
}
