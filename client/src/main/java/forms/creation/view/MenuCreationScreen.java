package forms.creation.view;

import forms.viwer.view.MenuViewerScreen;
import model.MenuDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by 1 on 19.12.2016.
 */
public class MenuCreationScreen extends JFrame{
    private static final String NAME  = "Введите имя :";
    private static final String TITLE = "Создание меню";
    private static final String OK = "OK";
    private static final long serialVersionUID = -1920089338703525376L;

    private JLabel nameMenuLabel;
    private JTextField nameMenuTextField;
    private JButton okButton;

    private final MenuViewerScreen viewScreen;
    private CreationPresenter presenter;

    public MenuCreationScreen(MenuViewerScreen viewScreen) {
        super(TITLE);
        this.viewScreen = viewScreen;
        MenuViewerScreen mvs = new MenuViewerScreen();
        this.setSize(new Dimension(300,150));
        JPanel panel = new JPanel();
        this.add(panel);
        panel.setLayout(new FlowLayout());
        panel.add(getNameMenuLabel());
        panel.add(getNameMenuTextField());
        panel.add(getOkButton());

        okButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                createMenu();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

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
        }
        return nameMenuTextField;
    }

    private JButton getOkButton() {
        if (okButton == null) {
            okButton = new JButton(OK);
            //openButton.setSize(50, 30);
        }
        return okButton;
    }

    private void createMenu() {
        if(nameMenuTextField.getText().length() > 0){
            presenter.createMenu(nameMenuTextField.getText());
        } else{
            JOptionPane.showMessageDialog(this, "Длина названия должна быть больше нуля!", "Ошибка ввода названия", JOptionPane.ERROR_MESSAGE);
        }
    }

    void openMenu(MenuDTO menu){
        if(menu != null){
            viewScreen.setCurrentMenu(menu);
            viewScreen.setVisible(true);
            this.setVisible(false);
        }
    }
}
