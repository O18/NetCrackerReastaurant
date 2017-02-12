package forms.selection;

import forms.creation.MenuCreationScreen;
import forms.viwer.MenuViewerScreen;
import model.MenuDTO;

import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class MenuSelectionScreen extends JFrame {
    private static final String SELECTION_OF_MENU = "Выбор меню";
    private static final String LIST_OF_MENU = "Выберите меню из списка для загрузки:";
    private static final String OPEN = "Открыть";
    private static final String CREATE = "Создать";
    private static final String DELETE = "Удалить";
    private static final long serialVersionUID = 2787597861798675816L;

    private SelectionPresenter presenter;
    private MenuViewerScreen viewScreen;
    private MenuCreationScreen createScreen;

    private JList<String> menuList;
    private JButton openButton;
    private JButton deleteButton;

    public MenuSelectionScreen(MenuViewerScreen viewScreen, MenuCreationScreen createScreen) {
        super(SELECTION_OF_MENU);
        this.viewScreen = viewScreen;
        this.createScreen = createScreen;
        menuList = getMenuList();
        JLabel listOfMenuLabel = createLabel(LIST_OF_MENU);
        openButton = createButton(OPEN, false);
        JButton createButton = createButton(CREATE, true);
        deleteButton = createButton(DELETE, false);
        //корневая панель
        JPanel rootPanel = new JPanel();
        this.add(rootPanel);
        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints constraints = new GridBagConstraints();
        rootPanel.setLayout(gbl);

        //добавление надписи LIST_OF_MENU
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 3;
        constraints.insets = new Insets(0, 5, 0, 0);
        rootPanel.add(listOfMenuLabel, constraints);

        //добавление списка меню
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 3;
        constraints.weightx = 1.0;
        constraints.insets = new Insets(5, 20, 10, 20);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        JScrollPane scrollPane = new JScrollPane(menuList);
        scrollPane.setPreferredSize(new Dimension(200, 180));
        rootPanel.add(scrollPane, constraints);

        //добавление кнопок Создать - Открыть - Удалить
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));
        buttonsPanel.add(createButton);
        buttonsPanel.add(Box.createHorizontalGlue());
        buttonsPanel.add(openButton);
        buttonsPanel.add(Box.createHorizontalGlue());
        buttonsPanel.add(deleteButton);
        rootPanel.add(buttonsPanel, constraints);

        openButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (openButton.isEnabled()) {
                    String selectedMenuName = menuList.getSelectedValue();
                    if (selectedMenuName != null)
                        presenter.getMenuByName(selectedMenuName);
                }
            }
        });
        createButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                openCreationScreen();
            }
        });
        deleteButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (deleteButton.isEnabled()) {
                    String selectedMenuName = menuList.getSelectedValue();
                    int choice = JOptionPane.showConfirmDialog(MenuSelectionScreen.this, "Удалить меню " + selectedMenuName + "?", "Подтверждение удаления", JOptionPane.YES_NO_OPTION);
                    if (choice == JOptionPane.YES_OPTION) {
                        presenter.deleteMenu(selectedMenuName);
                        presenter.getMenuNamesList();
                        openButton.setEnabled(false);
                        deleteButton.setEnabled(false);
                    }
                }
            }
        });
        menuList.addListSelectionListener(e -> {
            openButton.setEnabled(true);
            deleteButton.setEnabled(true);
        });
        this.pack();
        this.setMinimumSize(getSize());
    }

    public void setPresenter(SelectionPresenter presenter) {
        this.presenter = presenter;
    }

    public void updateMenusList() {
        if (presenter != null)
            presenter.getMenuNamesList();
    }

    void openMenu(MenuDTO menu, String menuName) {
        if (menu != null) {
            viewScreen.setCurrentMenu(menu, menuName);
            viewScreen.setVisible(true);
            this.setVisible(false);
        }
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        return label;
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

    void setMenuList(List<String> menuListNames) {
        String[] arrayMenuNames = new String[menuListNames.size()];
        menuList.setListData(menuListNames.toArray(arrayMenuNames));
    }

    private void openCreationScreen() {
        createScreen.setVisible(true);
        this.setVisible(false);
    }

    void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    private JButton createButton(String text, boolean enable) {
        JButton button = new JButton(text);
        button.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
        button.setEnabled(enable);

        return button;
    }
}
