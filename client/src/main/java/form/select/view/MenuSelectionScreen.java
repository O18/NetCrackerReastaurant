package form.select.view;

import form.MenuCreateScreen;
import form.MenuViewScreen;
import model.MenuDTO;

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
    private JList<MenuDTO> menuList;
    private JButton openButton;
    private JButton createButton;


    public MenuSelectionScreen() {
        super(SELECTION_OF_MENU);
        this.setSize(450, 220);
        menuList = getMenuList();
        listOfMenuLabel = getLabelListOfMenu();
        openButton = getOpenButton();
        createButton = getCreateButton();
        //корневая панель
        JPanel panel = new JPanel();
        this.add(panel);
        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        panel.setLayout(gbl);
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0,5,0,0);
        //gbc.fill = GridBagConstraints.BOTH;
        panel.add(listOfMenuLabel,gbc);
        gbc.gridy =1;
        gbc.gridheight=2;
        gbc.anchor = GridBagConstraints.SOUTH;
        gbc.insets = new Insets(5,5,0,0);
        JScrollPane jsp = new JScrollPane(menuList);
        panel.add(jsp,gbc);
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0,0,0,0);
        panel.add(openButton,gbc);
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.NORTH;
        panel.add(createButton,gbc);
    }

    private JLabel getLabelListOfMenu() {
        if (listOfMenuLabel == null) {
            listOfMenuLabel = new JLabel();
            listOfMenuLabel.setText(LIST_OF_MENU);
            listOfMenuLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        }
        return listOfMenuLabel;
    }

    private JList<MenuDTO> getMenuList() {
        if (menuList == null) {
            MenuDTO[] arr = new MenuDTO[]{new MenuDTO(), new MenuDTO(), new MenuDTO(), new MenuDTO(), new MenuDTO(), new MenuDTO(), new MenuDTO()};
            menuList = new JList<>(arr);
            menuList.setVisibleRowCount(5);
            menuList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            menuList.setSize(300, 180);
            menuList.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
        }
        return menuList;
    }

    private JButton getOpenButton() {
        if (openButton == null) {
            openButton = new JButton(OPEN);
            //openButton.setSize(50, 30);
            openButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
            openButton.setEnabled(false);
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
    private void createAction(MenuCreateScreen mcs) {
        OpenNextScreenAction createAction = new OpenNextScreenAction(this,mcs);
        createButton.addActionListener(createAction);
    }
    private void openAction (MenuViewScreen mvs) {
        OpenNextScreenAction openAction = new OpenNextScreenAction(this,mvs);
        openButton.addActionListener(openAction);
    }

    public static void main(String[] args) {
        MenuSelectionScreen mss = new MenuSelectionScreen();
        MenuCreateScreen mcs = new MenuCreateScreen();
        MenuViewScreen mvs = new MenuViewScreen();
        mss.setLocationRelativeTo(null);
        mss.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mss.createAction(mcs);
        mss.menuList.addListSelectionListener(
                e -> {
                    Object element = mss.menuList.getSelectedValue();//выбранный элемент в списке(понадобится для открытия определенного меню)
                    mss.openButton.setEnabled(true);
                    mss.openAction(mvs);
                });

        mss.setVisible(true);
    }
}
