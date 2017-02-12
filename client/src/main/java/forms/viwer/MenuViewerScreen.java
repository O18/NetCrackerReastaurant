package forms.viwer;

import forms.selection.MenuSelectionScreen;
import model.CategoryDTO;
import model.DishDTO;
import model.MenuDTO;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
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
    private CategoryDTO addedCategory;//todo сделать запоминание созданной или переименованной категории для ее выбора при перезагрузки меню
    private CategoryDTO categoryToEdit;
    private String nameDishToEdit;
    private boolean addOrChangeDish;

    public MenuViewerScreen() {
        super(TITLE);
        initComponents();

        this.pack();
        this.setMinimumSize(getSize());
        removeChangeButton.setVisible(false);
        saveChangeButton.setVisible(false);
    }

    private void initComponents() {
        JPanel rootPanel = new JPanel();
        this.add(rootPanel);
        GridBagLayout gridBagLayout = new GridBagLayout();
        rootPanel.setLayout(gridBagLayout);

        JMenuBar menuBar = initMenuBar();
        this.setJMenuBar(menuBar);

        GridBagConstraints constraints = new GridBagConstraints();
        JPanel categoryPanel = initCategoryPanel();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1.0;
        constraints.weighty = 0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(10, 5, 0, 0);
        rootPanel.add(categoryPanel, constraints);

        JPanel categoryButtonsPanel = initCategoryButtonsPanel();
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.insets = new Insets(10, 0, 0, 5);
        rootPanel.add(categoryButtonsPanel, constraints);

        JPanel dishesPanel = initDishesPanel();
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.weighty = 1.0;
        constraints.insets = new Insets(0, 5, 10, 0);
        rootPanel.add(dishesPanel, constraints);

        JPanel dishesButtonsPanel = initDishesButtonsPanel();
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.insets = new Insets(0, 0, 10, 5);
        rootPanel.add(dishesButtonsPanel, constraints);

    }

    private JMenuBar initMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu backToSelectionMenuButton = new JMenu(OPEN);
        menuBar.add(backToSelectionMenuButton);
        backToSelectionMenuButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selectionScreen.setVisible(true);
                MenuViewerScreen.super.setVisible(false);
            }
        });

        return menuBar;
    }

    private JPanel initCategoryPanel() {
        selectionCategoryBox = getSelectionCategoryBox();
        selectionCategoryBox.addItemListener(e -> {
            if (selectionCategoryBox.getSelectedIndex() != -1)
                setDataAboutDishes((CategoryDTO) selectionCategoryBox.getSelectedItem());
        });

        JTextField categoryEditor = (JTextField) selectionCategoryBox.getEditor().getEditorComponent();
        categoryEditor.addActionListener(e -> {
            String categoryName = e.getActionCommand();
            if (categoryToEdit == null) {
                CategoryDTO newCategory = new CategoryDTO(categoryName);
                presenter.addCategory(newCategory, currentMenuName);
            } else {
                presenter.changeCategory(categoryName, categoryToEdit.getName(), currentMenuName);
            }

            selectionCategoryBox.setEditable(false);
        });
        categoryEditor.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                selectionCategoryBox.setEditable(false);
                //todo обработать потерю фокуса
            }
        });

        selectionCategoryBox.addItemListener(e -> {
            if (selectionCategoryBox.getSelectedIndex() != -1) {
                deleteCategoryButton.setEnabled(true);
                editCategoryButton.setEnabled(true);
                setDataAboutDishes((CategoryDTO) selectionCategoryBox.getSelectedItem());
            } else {
                deleteCategoryButton.setEnabled(false);
                editCategoryButton.setEnabled(false);
            }
        });

        JLabel categoryLabel = new JLabel("Категория:");

        JPanel categoryPanel = new JPanel();
        categoryPanel.setLayout(new BoxLayout(categoryPanel, BoxLayout.Y_AXIS));
        categoryPanel.add(categoryLabel);
        categoryPanel.add(selectionCategoryBox);

        return categoryPanel;
    }

    private JPanel initCategoryButtonsPanel() {
        addCategoryButton = getAddCategoryButton();
        addCategoryButton.addActionListener(e -> {
            selectionCategoryBox.setEditable(true);
            selectionCategoryBox.setSelectedIndex(-1);
            selectionCategoryBox.requestFocusInWindow();
            categoryToEdit = null;
        });

        deleteCategoryButton = getDeleteCategoryButton();
        deleteCategoryButton.addActionListener(e -> {
            String categoryName = ((CategoryDTO) selectionCategoryBox.getSelectedItem()).getName();
            int choice = JOptionPane.showConfirmDialog(MenuViewerScreen.this, "Удалить категорию " + categoryName + "?", "Подтверждение удаления", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                presenter.deleteCategoryEvent(((CategoryDTO) selectionCategoryBox.getSelectedItem()).getName(), currentMenuName);
            }
            selectionCategoryBox.setEditable(false);
        });

        editCategoryButton = getEditCategoryButton();
        editCategoryButton.addActionListener(e -> {
            selectionCategoryBox.setEditable(true);
            selectionCategoryBox.requestFocusInWindow();
            categoryToEdit = (CategoryDTO) selectionCategoryBox.getSelectedItem();
        });

        JPanel categoryButtonsPanel = new JPanel();
        categoryButtonsPanel.setLayout(new BoxLayout(categoryButtonsPanel, BoxLayout.X_AXIS));
        categoryButtonsPanel.add(addCategoryButton);
        categoryButtonsPanel.add(Box.createHorizontalStrut(10));
        categoryButtonsPanel.add(deleteCategoryButton);
        categoryButtonsPanel.add(Box.createHorizontalStrut(10));
        categoryButtonsPanel.add(editCategoryButton);

        return categoryButtonsPanel;
    }

    private JPanel initDishesPanel() {
        dishesTable = getDishesTable();

        JPanel dishesPanel = new JPanel();
        JScrollPane dishesScrollPane = new JScrollPane(dishesTable);
        dishesTable.setFillsViewportHeight(true);
        dishesTable.setMinimumSize(dishesTable.getSize());
        dishesScrollPane.setMinimumSize(dishesScrollPane.getSize());
        dishesPanel.add(dishesScrollPane);

        return dishesPanel;
    }

    private JPanel initDishesButtonsPanel() {
        JPanel dishesButtonPanel = new JPanel();
        addDishButton = getAddDishButton();
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

        removeDishButton = getRemoveDishButton();
        removeDishButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                presenter.deleteDish(dishesTable.getValueAt(dishesTable.getSelectedRow(), 0).toString(), currentMenuName, selectionCategoryBox.getSelectedItem().toString());
                setDataAboutDishes((CategoryDTO) selectionCategoryBox.getSelectedItem());
            }
        });

        editDishButton = getEditDishButton();
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

        saveChangeButton = getSaveChangeButton();
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

        removeChangeButton = getRemoveChangeButton();
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

        dishesButtonPanel.setLayout(new BoxLayout(dishesButtonPanel, BoxLayout.X_AXIS));
        dishesButtonPanel.add(addDishButton);
        dishesButtonPanel.add(Box.createHorizontalStrut(10));
        dishesButtonPanel.add(removeDishButton);
        dishesButtonPanel.add(Box.createHorizontalStrut(10));
        dishesButtonPanel.add(editDishButton);

        return dishesButtonPanel;
    }

    public void setCurrentMenu(MenuDTO currentMenu, String currentMenuName) {
        this.currentMenuName = currentMenuName;
        selectionCategoryBox.setSelectedIndex(selectionCategoryBox.getItemCount() > 0 ? 0 : -1);
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
            dataAboutDishes = new Vector<>();
            dishesTableModel = (DefaultTableModel) dishesTable.getModel();
            columnHeader.add(NAME_DISH);
            columnHeader.add(PRICE_DISH);
            dishesTableModel.setColumnIdentifiers(columnHeader);
            dishesTableModel.addTableModelListener(dishesTable);
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

    private void setDishesTable() {
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

    private JButton createButton(String text, Font font, boolean enabled) {
        JButton button = new JButton(text);
        button.setFont(font);
        setEnabled(enabled);

        return button;
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

    private class DishesTableModel extends AbstractTableModel {

        private final String[] columnNames = {NAME_DISH, PRICE_DISH};
        private final List<DishDTO> dishes;

        private DishesTableModel(List<DishDTO> dishes) {
            this.dishes = dishes;
        }

        public void clear() {
            fireTableRowsDeleted(0, dishes.size() - 1);

            dishes.clear();
        }

        public void update(List<DishDTO> dishesToAdd) {
            dishes.clear();
            for (DishDTO dish : dishesToAdd) {
                dishes.add(dish);
            }

            fireTableRowsInserted(0, dishes.size() - 1);
        }

        public void add(DishDTO dish) {
            dishes.add(dish);

            fireTableRowsInserted(dishes.size() - 1, dishes.size() - 1);
        }

        @Override
        public int getRowCount() {
            return dishes.size();
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            switch (columnIndex) {
                case 0:
                    return dishes.get(rowIndex).getDishName();
                case 1:
                    return dishes.get(rowIndex).getCost();
            }

            throw new IllegalArgumentException();
        }
    }
}