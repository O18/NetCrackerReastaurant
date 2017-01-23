package com.o18.restaurant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by 1 on 19.12.2016.
 */
public class MenuCreateScreen extends JFrame{
    private static final String NAME  = "Введите имя :";
    private static final String TITLE = "Создание меню";
    private static final String OK = "OK";
    private JLabel nameMenuLabel;
    private JTextField nameMenuTextField;
    private JButton okButton;

    public MenuCreateScreen() {
        super(TITLE);
        MenuViewScreen mvs = new MenuViewScreen();
        this.setSize(new Dimension(300,150));
        JPanel panel = new JPanel();
        this.add(panel);
        panel.setLayout(new FlowLayout());
        panel.add(getNameMenuLabel());
        panel.add(getNameMenuTextField());
        panel.add(getOkButton());
        OpenNextScreenAction createAction = new OpenNextScreenAction(this,mvs);
        okButton.addActionListener(createAction);
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

    private class OpenNextScreenAction implements ActionListener {
        private JFrame newFrame;
        private JFrame oldFrame;
        public OpenNextScreenAction(JFrame oldFrame, JFrame newFrame) {
            this.newFrame = newFrame;
            this.oldFrame = oldFrame;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            oldFrame.setVisible(false);
            newFrame.setVisible(true);
        }
    }
}
