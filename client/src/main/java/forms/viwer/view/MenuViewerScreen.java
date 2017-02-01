package forms.viwer.view;

import forms.selection.view.MenuSelectionScreen;
import model.CategoryDTO;
import model.MenuDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by 1 on 27.12.2016.
 */
public class MenuViewerScreen extends JFrame {
    private boolean addOrEdit;

    private static final String TITLE = "Работа с меню";
    private static final String OPEN = "Открыть";
    private static final String ADD = "+";
    private static final String REMOVE = "-";
    private static final String EDIT = "Изменить";
    private static final String SAVE_CHANGE = "V";
    private static final String REMOVE_CHANGE = "X";

    private String[][] dataAboutDishes = {{"Картофель фри", "150"}, {"Овощи", "200"}};
    private static final String[] COLUMN_HEADER = {"Название блюда", "Цена"};


    private ViewerPresenter presenter;
    private static final long serialVersionUID = -9135268706317372751L;

    private JMenu backToSelectionMenuButton;
    private JComboBox<CategoryDTO> selectionCategoryBox;
    private JButton addCategoryButton;
    private JButton removeCategoryButton;
    private JButton editCategoryButton;
    private JTextField addAndEditNameCategory;
    private JButton saveChangeCategoriesButton;
    private JButton removeChangeCategoriesButton;
    private JTable dishesTable;
    private JButton addDishButton;
    private JButton removeDishButton;
    private JButton editDishButton;

    private MenuSelectionScreen selectionScreen;

    private MenuDTO currentMenu;
    private String currentMenuName;

    public MenuViewerScreen() {
        super(TITLE);
        addCategoryButton = getAddCategoryButton();
        removeCategoryButton = getRemoveCategoryButton();
        editCategoryButton = getEditCategoryButton();
        selectionCategoryBox = getSelectionCategoryBox();
        addAndEditNameCategory = getAddAndEditNameCategory();
        saveChangeCategoriesButton = getSaveChangeCategoriesButton();
        removeChangeCategoriesButton = getRemoveChangeCategoriesButton();
        addDishButton = getAddDishButton();
        removeDishButton = getRemoveDishButton();
        editDishButton = getEditDishButton();
        dishesTable = getDishesTable();


        //создание JMenuBar с кнопкой Открыть
        JMenuBar menuBar = new JMenuBar();
        backToSelectionMenuButton = new JMenu(OPEN);
        menuBar.add(backToSelectionMenuButton);
        this.setJMenuBar(menuBar);
        //создание JPanel и GridBagLayout
        JPanel rootPanel = new JPanel();
        this.add(rootPanel);
        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        rootPanel.setLayout(gbl);
        //создание JComboBox для категорий блюд
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 5, 0, 0);
        rootPanel.add(selectionCategoryBox, gbc);
        //создание панели с кнопками добавить, удалить, изменить для КАТЕГОРИИ
        gbc.gridy = 0;
        gbc.gridx = 1;
        gbc.weightx = 0;
        JPanel categoryButtonsPanel = new JPanel();
        categoryButtonsPanel.setLayout(new BoxLayout(categoryButtonsPanel, BoxLayout.X_AXIS));
        categoryButtonsPanel.add(addCategoryButton);
        categoryButtonsPanel.add(Box.createHorizontalGlue());
        categoryButtonsPanel.add(removeCategoryButton);
        categoryButtonsPanel.add(Box.createHorizontalGlue());
        categoryButtonsPanel.add(editCategoryButton);
        rootPanel.add(categoryButtonsPanel, gbc);
        //создание панели со скроллом и таблицей с блюдами
        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        JScrollPane dishesScrollPane = new JScrollPane(dishesTable);
        dishesTable.setFillsViewportHeight(true);
        rootPanel.add(dishesScrollPane, gbc);
        //создание панели с кнопками добавить, удалить, изменить для БЛЮДА
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.gridy = 1;
        gbc.gridx = 1;
        JPanel dishButtonsPanel = new JPanel();
        dishButtonsPanel.setLayout(new BoxLayout(dishButtonsPanel, BoxLayout.X_AXIS));
        dishButtonsPanel.add(addDishButton);
        dishButtonsPanel.add(Box.createHorizontalGlue());
        dishButtonsPanel.add(removeDishButton);
        dishButtonsPanel.add(Box.createHorizontalGlue());
        dishButtonsPanel.add(editDishButton);
        rootPanel.add(dishButtonsPanel, gbc);
        //создание слушателей для кнопок
        backToSelectionMenuButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selectionScreen.setVisible(true);
                MenuViewerScreen.super.setVisible(false);
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
        addCategoryButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selectionCategoryBox.setEditable(true);
                selectionCategoryBox.addItem(new CategoryDTO(""));
                selectionCategoryBox.setSelectedIndex(selectionCategoryBox.getItemCount() - 1);
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
        removeCategoryButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                presenter.deleteCategoryEvent(selectionCategoryBox.getSelectedItem().toString(), currentMenuName);
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
        editCategoryButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                addAndEditNameCategory.setVisible(true);
                saveChangeCategoriesButton.setVisible(true);
                removeChangeCategoriesButton.setVisible(true);
                addOrEdit = false;
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
        saveChangeCategoriesButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                addAndEditNameCategory.setVisible(false);
                saveChangeCategoriesButton.setVisible(false);
                removeChangeCategoriesButton.setVisible(false);
                if (addOrEdit) {
                    presenter.addCategory(new CategoryDTO(addAndEditNameCategory.getText()), currentMenuName);
                } else {
                    presenter.changeCategory(new CategoryDTO(addAndEditNameCategory.getText()), selectionCategoryBox.getSelectedItem().toString(), currentMenuName);
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
        removeChangeCategoriesButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                addAndEditNameCategory.setVisible(false);
                saveChangeCategoriesButton.setVisible(false);
                removeChangeCategoriesButton.setVisible(false);
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

        selectionCategoryBox.addActionListener(e -> {
            if(e.getActionCommand().equals("comboBoxEdited")){
                if(selectionCategoryBox.getSelectedIndex() == -1){
                    String newCategoryName = (String) selectionCategoryBox.getSelectedItem();
                    CategoryDTO newCategory = new CategoryDTO(newCategoryName);
                    presenter.addCategory(newCategory, currentMenuName);
                }else {
                    CategoryDTO newCategory = (CategoryDTO) selectionCategoryBox.getSelectedItem();
                    String oldName = selectionCategoryBox.getItemAt(selectionCategoryBox.getSelectedIndex()).getName();
                    presenter.changeCategory(newCategory, oldName, currentMenuName);
                }
            }

        });

        selectionCategoryBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {

            }
        });
        //упакуем
        this.pack();
        this.setMinimumSize(getSize());
    }

    public void setCurrentMenu(MenuDTO currentMenu, String currentMenuNameName) {
        this.currentMenu = currentMenu;
        this.currentMenuName = currentMenuNameName;
        selectionCategoryBox.removeAllItems();
        for (CategoryDTO category : currentMenu.getCategories()) {
            selectionCategoryBox.addItem(category);
        }
    }

    private JComboBox<CategoryDTO> getSelectionCategoryBox() {
        if (selectionCategoryBox == null) {
            selectionCategoryBox = new JComboBox<>();
            selectionCategoryBox.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        }
        return selectionCategoryBox;
    }

    private JButton getAddCategoryButton() {
        if (addCategoryButton == null) {
            addCategoryButton = new JButton(ADD);
            addCategoryButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 22));
        }
        return addCategoryButton;
    }

    private JButton getRemoveCategoryButton() {
        if (removeCategoryButton == null) {
            removeCategoryButton = new JButton(REMOVE);
            removeCategoryButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 22));
        }
        return removeCategoryButton;
    }

    private JButton getEditCategoryButton() {
        if (editCategoryButton == null) {
            editCategoryButton = new JButton(EDIT);
            editCategoryButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        }
        return editCategoryButton;
    }

    private JTextField getAddAndEditNameCategory() {
        if (addAndEditNameCategory == null) {
            addAndEditNameCategory = new JTextField(20);
            addAndEditNameCategory.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
            addAndEditNameCategory.setVisible(false);
        }
        return addAndEditNameCategory;
    }

    private JButton getSaveChangeCategoriesButton() {
        if (saveChangeCategoriesButton == null) {
            saveChangeCategoriesButton = new JButton(SAVE_CHANGE);
            saveChangeCategoriesButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
            saveChangeCategoriesButton.setVisible(false);
        }
        return saveChangeCategoriesButton;
    }

    private JButton getRemoveChangeCategoriesButton() {
        if (removeChangeCategoriesButton == null) {
            removeChangeCategoriesButton = new JButton(REMOVE_CHANGE);
            removeChangeCategoriesButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
            removeChangeCategoriesButton.setVisible(false);
        }
        return removeChangeCategoriesButton;
    }

    private JTable getDishesTable() {
        if (dishesTable == null) {
            dishesTable = new JTable(dataAboutDishes, COLUMN_HEADER);
            dishesTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
            dishesTable.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        }
        return dishesTable;
    }


    public void setSelectionScreen(MenuSelectionScreen selectionScreen) {
        this.selectionScreen = selectionScreen;
    }

    public void setPresenter(ViewerPresenter presenter) {
        this.presenter = presenter;
    }

    private JButton getAddDishButton() {
        if (addDishButton == null) {
            addDishButton = new JButton(ADD);
            addDishButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 22));
        }
        return addDishButton;
    }

    private JButton getRemoveDishButton() {
        if (removeDishButton == null) {
            removeDishButton = new JButton(REMOVE);
            removeDishButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 22));
        }
        return removeDishButton;
    }

    private JButton getEditDishButton() {
        if (editDishButton == null) {
            editDishButton = new JButton(EDIT);
            editDishButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        }
        return editDishButton;
    }


}