package forms.viwer;

import forms.selection.MenuSelectionScreen;
import model.CategoryDTO;
import model.DishDTO;
import model.MenuDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;


public class MenuViewerScreen extends JFrame {
    private static final String TITLE = "Работа с меню";
    private static final String OPEN = "Открыть";
    private static final String ADD = "+";
    private static final String REMOVE = "-";
    private static final String EDIT = "Изменить";
    private static final String NAME_DISH = "Название блюда";
    private static final String PRICE_DISH = "Цена";

    private Vector<DishDTO> dataAboutDishes;
    private Vector<String> columnHeader = new Vector<>(2);
    private static final long serialVersionUID = -9135268706317372751L;

    private JComboBox<CategoryDTO> selectionCategoryBox;
    private JButton addCategoryButton;
    private JButton deleteCategoryButton;
    private JButton editCategoryButton;
    private JTable dishesTable;
    private JButton addDishButton;
    private JButton removeDishButton;
    private JButton editDishButton;
    private JButton saveChangeButton;
    private JButton removeChangeButton;

    private DefaultTableModel dishesTableModel;
    private ViewerPresenter presenter;
    private MenuSelectionScreen selectionScreen;

    private String currentMenuName;
    private CategoryDTO categoryToEdit;
    private String nameDishToEdit;
    private boolean addOrChangeDish;

    public MenuViewerScreen() {
        super(TITLE);
        addCategoryButton = getAddCategoryButton();
        deleteCategoryButton = getDeleteCategoryButton();
        editCategoryButton = getEditCategoryButton();
        selectionCategoryBox = getSelectionCategoryBox();
        addDishButton = getAddDishButton();
        removeDishButton = getRemoveDishButton();
        editDishButton = getEditDishButton();
        saveChangeButton = getSaveChangeButton();
        removeChangeButton = getRemoveChangeButton();

        dishesTable = getDishesTable();
        dishesTableModel = (DefaultTableModel) dishesTable.getModel();
        columnHeader.add(NAME_DISH);
        columnHeader.add(PRICE_DISH);
        dishesTableModel.setColumnIdentifiers(columnHeader);


        //создание JMenuBar с кнопкой Открыть
        JMenuBar menuBar = new JMenuBar();
        JMenu backToSelectionMenuButton = new JMenu(OPEN);
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
        categoryButtonsPanel.add(Box.createHorizontalBox());
        categoryButtonsPanel.add(deleteCategoryButton);
        categoryButtonsPanel.add(Box.createHorizontalBox());
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
        dishesTable.setMinimumSize(dishesTable.getSize());
        dishesScrollPane.setMinimumSize(dishesScrollPane.getSize());
        //создание панели с кнопками добавить, удалить, изменить для БЛЮДА
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.gridy = 1;
        gbc.gridx = 1;
        JPanel dishButtonsPanel = new JPanel();
        dishButtonsPanel.setLayout(new BoxLayout(dishButtonsPanel, BoxLayout.X_AXIS));
        dishButtonsPanel.add(addDishButton);
        dishButtonsPanel.add(Box.createHorizontalStrut(10));
        dishButtonsPanel.add(removeDishButton);
        dishButtonsPanel.add(Box.createHorizontalStrut(10));
        dishButtonsPanel.add(editDishButton);
        rootPanel.add(dishButtonsPanel, gbc);
        //создание панели с кнопками для сохранения и удаления изменений в блюдах
        gbc.gridx = 0;
        gbc.gridy = 2;
        JPanel changePanel = new JPanel();
        changePanel.setLayout(new FlowLayout());
        changePanel.add(saveChangeButton);
        changePanel.add(removeChangeButton);
        rootPanel.add(changePanel, gbc);

        dataAboutDishes = new Vector<>();
        dishesTableModel.addTableModelListener(dishesTable);


        //обновление таблицы при перемене категории
        selectionCategoryBox.addItemListener(e -> {
            if (selectionCategoryBox.getSelectedIndex() != -1)
                setDataAboutDishes((CategoryDTO) selectionCategoryBox.getSelectedItem());
        });

        //создание слушателей для кнопок изменения списка категорий
        backToSelectionMenuButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selectionScreen.setVisible(true);
                MenuViewerScreen.super.setVisible(false);
            }
        });
        addCategoryButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selectionCategoryBox.setEditable(true);
                selectionCategoryBox.setSelectedIndex(-1);
                categoryToEdit = null;
            }
        });
        deleteCategoryButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (selectionCategoryBox.getSelectedIndex() != -1) {
                    String categoryName = ((CategoryDTO) selectionCategoryBox.getSelectedItem()).getName();
                    int choice = JOptionPane.showConfirmDialog(MenuViewerScreen.this, "Удалить категорию " + categoryName + "?", "Подтверждение удаления", JOptionPane.YES_NO_OPTION);
                    if (choice == JOptionPane.YES_OPTION) {
                        presenter.deleteCategoryEvent(((CategoryDTO) selectionCategoryBox.getSelectedItem()).getName(), currentMenuName);
                    }
                    selectionCategoryBox.setEditable(false);
                } else {
                    JOptionPane.showMessageDialog(MenuViewerScreen.this, "Не выбрана категория");
                }
            }
        });
        editCategoryButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (selectionCategoryBox.getSelectedIndex() != -1) {
                    selectionCategoryBox.setEditable(true);
                    categoryToEdit = (CategoryDTO) selectionCategoryBox.getSelectedItem();

                } else {
                    JOptionPane.showMessageDialog(MenuViewerScreen.this, "Не выбрана категория");
                }
            }
        });

        selectionCategoryBox.addActionListener(e -> {
            if (e.getActionCommand().equals("comboBoxEdited")) {
                if (categoryToEdit == null && selectionCategoryBox.getSelectedIndex() == -1) {
                    String newCategoryName = (String) selectionCategoryBox.getSelectedItem();
                    CategoryDTO newCategory = new CategoryDTO(newCategoryName);
                    presenter.addCategory(newCategory, currentMenuName);
                } else {
                    if (selectionCategoryBox.getSelectedIndex() == -1) {
                        String newCategoryName = (String) selectionCategoryBox.getSelectedItem();
                        presenter.changeCategory(newCategoryName, categoryToEdit.getName(), currentMenuName);
                    }
                }
                selectionCategoryBox.setEditable(false);
            }
        });

        selectionCategoryBox.addItemListener(e -> {
            if (selectionCategoryBox.getSelectedIndex() != -1) {
                setDataAboutDishes((CategoryDTO) selectionCategoryBox.getSelectedItem());
            }
        });

        addDishButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Vector newRow = new Vector(2);
                dishesTableModel.insertRow(dishesTableModel.getRowCount(), newRow);
                saveChangeButton.setVisible(true);
                removeChangeButton.setVisible(true);
                addOrChangeDish = true;
            }
        });
        removeDishButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                presenter.deleteDish(dishesTable.getValueAt(dishesTable.getSelectedRow(), 0).toString(), currentMenuName, selectionCategoryBox.getSelectedItem().toString());
                setDataAboutDishes((CategoryDTO) selectionCategoryBox.getSelectedItem());
            }
        });

        editDishButton.addMouseListener(new MouseListener() {//todo заменить на событие таблицы
            @Override
            public void mouseClicked(MouseEvent e) {
                saveChangeButton.setVisible(true);
                removeChangeButton.setVisible(true);
                nameDishToEdit = dishesTable.getValueAt(dishesTable.getSelectedRow(), 0).toString();
                addOrChangeDish = false;
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
        saveChangeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (addOrChangeDish) {
                    DishDTO dish = new DishDTO(dishesTable.getValueAt(dishesTable.getRowCount() - 1, 0).toString(), Double.parseDouble(dishesTable.getValueAt(dishesTable.getSelectedRow(), 1).toString()));
                    presenter.addDish(currentMenuName, dish, ((CategoryDTO) selectionCategoryBox.getSelectedItem()).getName());
                } else {
                    DishDTO newDish = new DishDTO(dishesTable.getValueAt(dishesTable.getSelectedRow(), 0).toString(), Double.parseDouble(dishesTable.getValueAt(dishesTable.getSelectedRow(), 1).toString()));
                    presenter.changeDish(newDish, nameDishToEdit, currentMenuName, selectionCategoryBox.getSelectedItem().toString());
                }
                setDataAboutDishes((CategoryDTO) selectionCategoryBox.getSelectedItem());
                saveChangeButton.setVisible(false);
                removeChangeButton.setVisible(false);
            }
        });
        removeChangeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (addOrChangeDish) {
                    dishesTableModel.removeRow(dishesTableModel.getRowCount() - 1);
                }
                saveChangeButton.setVisible(false);
                removeChangeButton.setVisible(false);
            }
        });


        //упакуем
        this.pack();
        this.setMinimumSize(getSize());
        removeChangeButton.setVisible(false);
        saveChangeButton.setVisible(false);
    }

    public void setCurrentMenu(MenuDTO currentMenu, String currentMenuName) {
        this.currentMenuName = currentMenuName;
        updateMenu(currentMenu);
    }

    void updateMenu(MenuDTO currentMenu) {
        int lastSelectedIndex = selectionCategoryBox.getSelectedIndex();
        selectionCategoryBox.removeAllItems();
        List<CategoryDTO> sortedList = new ArrayList<>(currentMenu.getCategories());
        sortedList.sort(Comparator.comparing(CategoryDTO::getName));
        for (CategoryDTO category : sortedList) {
            selectionCategoryBox.addItem(category);
        }

        if (lastSelectedIndex != -1) {
            selectionCategoryBox.setSelectedIndex(lastSelectedIndex);
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

    private JButton getDeleteCategoryButton() {
        if (deleteCategoryButton == null) {
            deleteCategoryButton = new JButton(REMOVE);
            deleteCategoryButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 22));
        }
        return deleteCategoryButton;
    }

    private JButton getEditCategoryButton() {
        if (editCategoryButton == null) {
            editCategoryButton = new JButton(EDIT);
            editCategoryButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        }
        return editCategoryButton;
    }

    private JTable getDishesTable() {
        if (dishesTable == null) {
            dishesTable = new JTable();
            dishesTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
            dishesTable.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
            dishesTable.setRowHeight(20);
        }
        return dishesTable;
    }

    //изменение таблицы
    void setDataAboutDishes(CategoryDTO category) {
        dataAboutDishes.removeAllElements();
        for (DishDTO dish : category.getDishes()) {
            dataAboutDishes.add(dish);
        }
        setDishesTable();
    }

    void setDishesTable() {
        dishesTableModel = new DefaultTableModel();
        dishesTable.setModel(dishesTableModel);
        dishesTableModel.setColumnIdentifiers(columnHeader);
        for (DishDTO dish : dataAboutDishes) {
            Vector<String> newRow = new Vector<>(2);
            newRow.add(dish.getDishName());
            newRow.add(Double.toString(dish.getCost()));
            dishesTableModel.addRow(newRow);
        }
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

    private JButton getSaveChangeButton() {
        if (saveChangeButton == null) {
            saveChangeButton = new JButton("V");
            saveChangeButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        }
        return saveChangeButton;
    }

    private JButton getRemoveChangeButton() {
        if (removeChangeButton == null) {
            removeChangeButton = new JButton("X");
            removeChangeButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        }
        return removeChangeButton;
    }

    void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}