package forms.creation.view;

import forms.selection.view.MenuSelectionScreen;
import forms.viwer.view.MenuViewerScreen;
import model.MenuDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by 1 on 19.12.2016.
 */
public class MenuCreationScreen extends JFrame{
    private static final String NAME  = "Введите имя :";
    private static final String TITLE = "Создание меню";
    private static final String OK = "OK";
    private static final String RETURN = "Вернуться к выбору меню";
    private static final long serialVersionUID = -1920089338703525376L;

    private JLabel nameMenuLabel;
    private JTextField nameMenuTextField;
    private JButton okButton;
    private JMenuBar menuCreationBar;

    private final MenuViewerScreen viewScreen;
    private MenuSelectionScreen selectionScreen;
    private CreationPresenter presenter;

    public MenuCreationScreen(MenuViewerScreen viewScreen) {
        super(TITLE);
        this.viewScreen = viewScreen;
        this.setSize(new Dimension(320,150));
        this.setJMenuBar(getMenuCreationBar());
        JPanel panel = new JPanel();
        this.add(panel);
        panel.setLayout(new FlowLayout());
        panel.add(getNameMenuLabel());
        panel.add(getNameMenuTextField());
        panel.add(getOkButton());
        okButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                createMenu();
            }
        });
        menuCreationBar.getMenu(0).addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MenuCreationScreen.super.setVisible(false);
                selectionScreen.setVisible(true);

            }
        });
    }

    public void setPresenter(CreationPresenter presenter) {
        this.presenter = presenter;
    }

    private JLabel getNameMenuLabel() {
        if (nameMenuLabel == null) {
            nameMenuLabel = new JLabel();
            nameMenuLabel.setText(NAME);
            nameMenuLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        }
        return nameMenuLabel;
    }

    private JTextField getNameMenuTextField() {
        if (nameMenuTextField == null) {
            nameMenuTextField = new JTextField(20);
            nameMenuTextField.setSize(70,20);
            nameMenuTextField.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        }
        return nameMenuTextField;
    }

    private JButton getOkButton() {
        if (okButton == null) {
            okButton = new JButton(OK);
            okButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        }
        return okButton;
    }
    public JMenuBar getMenuCreationBar() {
        menuCreationBar = new JMenuBar();
        JMenu returnToSelectionMenu = new JMenu(RETURN);
        menuCreationBar.add(returnToSelectionMenu);
        menuCreationBar.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        return menuCreationBar;
    }

    private void createMenu() {
        if(nameMenuTextField.getText().length() > 0){
            presenter.createMenu(nameMenuTextField.getText());
        } else{
            JOptionPane.showMessageDialog(this, "Длина названия должна быть больше нуля!", "Ошибка ввода названия", JOptionPane.ERROR_MESSAGE);
        }
    }

    void openMenu(MenuDTO menu, String menuName){
        if(menu != null){
            viewScreen.setCurrentMenu(menu, menuName);
            viewScreen.setVisible(true);
            this.setVisible(false);
        }
    }

    public void setSelectionScreen(MenuSelectionScreen selectionScreen) {
        this.selectionScreen = selectionScreen;
    }

    void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}