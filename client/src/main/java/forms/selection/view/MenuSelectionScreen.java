package forms.selection.view;

import forms.creation.view.MenuCreationScreen;
import forms.viwer.view.MenuViewerScreen;
import model.MenuDTO;

import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

public class MenuSelectionScreen extends JFrame {
    private static final String SELECTION_OF_MENU = "Выбор меню";
    private static final String LIST_OF_MENU = "Выберите меню из списка для загрузки:";
    private static final String OPEN = "Открыть";
    private static final String CREATE = "Создать";
    private static final long serialVersionUID = 2787597861798675816L;

    private SelectionPresenter presenter;
    private MenuViewerScreen viewScreen;
    private MenuCreationScreen createScreen;

    private JLabel listOfMenuLabel;
    private JList<String> menuList;
    private JButton openButton;
    private JButton createButton;

    public MenuSelectionScreen(MenuViewerScreen viewScreen, MenuCreationScreen createScreen) {
        super(SELECTION_OF_MENU);
        this.viewScreen = viewScreen;
        this.createScreen = createScreen;
        this.setPreferredSize(new Dimension(450, 450));
        this.setMinimumSize(new Dimension(450, 300));
        menuList = getMenuList();
        listOfMenuLabel = getLabelListOfMenu();
        openButton = getOpenButton();
        createButton = getCreateButton();
        //корневая панель
        JPanel rootPanel = new JPanel();
        this.add(rootPanel);
        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints constraints = new GridBagConstraints();
        rootPanel.setLayout(gbl);

        constraints.anchor = GridBagConstraints.NORTH;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(0, 5, 0, 0);
        //constraints.fill = GridBagConstraints.BOTH;
        rootPanel.add(listOfMenuLabel, constraints);

        constraints.gridy = 1;
        constraints.gridheight = 2;
        constraints.anchor = GridBagConstraints.SOUTH;
        constraints.insets = new Insets(5, 5, 0, 0);
        JScrollPane scrollPane = new JScrollPane(menuList);
        scrollPane.setMinimumSize(new Dimension(200, 180));
        scrollPane.setPreferredSize(new Dimension(200, 180));
        rootPanel.add(scrollPane, constraints);

        constraints.gridx = 2;
        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(0, 0, 0, 0);
        rootPanel.add(openButton, constraints);

        constraints.gridx = 2;
        constraints.gridy = 2;
        constraints.anchor = GridBagConstraints.NORTH;
        rootPanel.add(createButton, constraints);

        openButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(openButton.isEnabled()) {
                    String selectedMenuName = menuList.getSelectedValue();
                    presenter.getMenuByName(selectedMenuName);
                }
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

        createButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                openCreationScreen();
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

        menuList.addListSelectionListener(e -> openButton.setEnabled(true));
    }

    public void setPresenter(SelectionPresenter presenter) {
        this.presenter = presenter;
    }

    public void updateMenusList(){
        if(presenter != null)
            presenter.getMenuNamesList();
    }

    void openMenu(MenuDTO menu, String menuName){
        if(menu != null){
            viewScreen.setCurrentMenu(menu, menuName);
            viewScreen.setVisible(true);
            this.setVisible(false);
        }
    }

    private JLabel getLabelListOfMenu() {
        if (listOfMenuLabel == null) {
            listOfMenuLabel = new JLabel();
            listOfMenuLabel.setText(LIST_OF_MENU);
            listOfMenuLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        }
        return listOfMenuLabel;
    }

    private JList<String> getMenuList() {
        if (menuList == null) {
            menuList = new JList<>();
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
            openButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
            openButton.setEnabled(false);
        }
        return openButton;
    }

    private JButton getCreateButton() {
        if (createButton == null) {
            createButton = new JButton(CREATE);
            createButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
        }
        return createButton;
    }

    void setMenuList(List<String> menuListNames){
        String[] arrayMenuNames = new String[menuListNames.size()];
        menuList.setListData(menuListNames.toArray(arrayMenuNames));
    }

    private void openCreationScreen() {
        createScreen.setVisible(true);
        this.setVisible(false);
    }
}
