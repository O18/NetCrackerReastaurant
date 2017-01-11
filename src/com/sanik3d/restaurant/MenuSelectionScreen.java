package com.sanik3d.restaurant;

import com.sanik3d.restaurant.model.Menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MenuSelectionScreen extends JFrame {
    private static final String SELECTION_OF_MENU = "Выбор меню";
    private static final String LIST_OF_MENU = "Выберите меню из списка для загрузки:";
    private static final String OPEN = "Открыть";
    private static final String CREATE = "Создать";

    private JLabel listOfMenuLabel;
    private JList<Menu> listOfMenu;
    private JButton openButton;
    private JButton createButton;


    private MenuSelectionScreen() {
        super(SELECTION_OF_MENU);
        this.setSize(450, 220);
        CreateMenuScreen cms = new CreateMenuScreen();
        MenuViewScreen mvs = new MenuViewScreen();
        //корневая панель
        JPanel panel = new JPanel();
        this.add(panel);
        /*деление панели на две части
        JPanel leftPanel = new JPanel();
        leftPanel.setSize(170,110);
        JPanel rightPanel = new JPanel();
        rightPanel.setSize(170,110);
        panel.setLayout(new BorderLayout());
        panel.add(leftPanel,BorderLayout.WEST);
        panel.add(rightPanel,BorderLayout.EAST);*/
        //левая панель
        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        panel.setLayout(gbl);
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0,5,0,0);
        //gbc.fill = GridBagConstraints.BOTH;
        gbl.setConstraints(getLabelListOfMenu(),gbc);
        panel.add(getLabelListOfMenu());
        gbc.gridy =1;
        gbc.gridheight=2;
        gbc.anchor = GridBagConstraints.SOUTH;
        gbc.insets = new Insets(5,5,0,0);
        JScrollPane jsp = new JScrollPane(getListOfMenu());
        gbl.setConstraints(jsp,gbc);
        panel.add(jsp);
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0,0,0,0);
        gbl.setConstraints(getOpenButton(),gbc);
        panel.add(getOpenButton());
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.NORTH;
        gbl.setConstraints(getCreateButton(),gbc);
        panel.add(getCreateButton());
        OpenNextScreenAction createAction = new OpenNextScreenAction(this,cms);
        OpenNextScreenAction openAction = new OpenNextScreenAction(this,mvs);
        createButton.addActionListener(createAction);
        openButton.addActionListener(openAction);
    }

    private JLabel getLabelListOfMenu() {
        if (listOfMenuLabel == null) {
            listOfMenuLabel = new JLabel();
            listOfMenuLabel.setText(LIST_OF_MENU);
            listOfMenuLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        }
        return listOfMenuLabel;
    }

    private JList<Menu> getListOfMenu() {
        if (listOfMenu == null) {
            Menu[] arr = new Menu[]{new Menu(), new Menu(), new Menu(), new Menu(), new Menu(), new Menu(), new Menu()};
            listOfMenu = new JList<>(arr);
            listOfMenu.setVisibleRowCount(5);
            listOfMenu.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            listOfMenu.setSize(300, 180);
            listOfMenu.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
        }
        return listOfMenu;
    }

    private JButton getOpenButton() {
        if (openButton == null) {
            openButton = new JButton(OPEN);
            //openButton.setSize(50, 30);
            openButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
        }
        return openButton;
    }

    private JButton getCreateButton() {
        if (createButton == null) {
            createButton = new JButton(CREATE);
            //createButton.setSize(40, 30);
            createButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
        }
        return createButton;
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

    public static void main(String[] args) {
        /*JFrame.setDefaultLookAndFeelDecorated(true);
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ...
            }
        });*/
        MenuSelectionScreen mss = new MenuSelectionScreen();
        mss.setVisible(true);

        //cms.setVisible(true);
        //mss.setVisible(true);
    }
}
