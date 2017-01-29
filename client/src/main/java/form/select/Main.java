package form.select;

import client.MenuClient;
import form.create.MenuCreateScreen;
import form.main.MenuViewScreen;
import form.select.view.MenuSelectionScreen;

import javax.swing.*;

/**
 * Created by User on 29.01.2017
 */
public class Main {

    public static void main(String[] args) {
        MenuClient client = new MenuClient();

        MenuSelectionScreen selectionScreen = new MenuSelectionScreen();
        MenuCreateScreen createScreen = new MenuCreateScreen();
        MenuViewScreen viewScreen = new MenuViewScreen();

        selectionScreen.setLocationRelativeTo(null);
        selectionScreen.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        createScreen.setLocationRelativeTo(null);
        createScreen.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        viewScreen.setLocationRelativeTo(null);
        viewScreen.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        selectionScreen.setVisible(true);
    }
}
