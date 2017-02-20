package forms.viwer;

import forms.selection.MenuSelectionScreen;
import model.CategoryDTO;
import model.DishDTO;
import model.MenuDTO;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellEditor;
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

    private static final long serialVersionUID = -9135268706317372751L;

    private JComboBox<CategoryDTO> selectionCategoryBox;
    private JButton addCategoryButton;
    private JButton deleteCategoryButton;
    private JButton editCategoryButton;

    private JTable dishesTable;
    private JButton addDishButton;
    private JButton deleteDishButton;

    private DishesTableModel dishesTableModel;
    private ViewerPresenter presenter;
    private MenuSelectionScreen selectionScreen;

    private String currentMenuName;
    private CategoryDTO lastChosenCategory;
    private CategoryDTO categoryToEdit;

    public MenuViewerScreen() {
        super(TITLE);
        initComponents();

        this.pack();
        this.setMinimumSize(getSize());
        this.setEnabled(true);
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
        constraints.insets = new Insets(10, 5, 0, 5);
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

        UIManager.put("OptionPane.cancelButtonText", "Отмена");
        UIManager.put("OptionPane.noButtonText", "Нет");
        UIManager.put("OptionPane.okButtonText", "Да");
        UIManager.put("OptionPane.font", new Font("Comic Sans MS", Font.PLAIN, 16));
        UIManager.put("OptionPane.inputDialogTitle", "Ввод");
        UIManager.put("OptionPane.messageDialogTitle", "Сообщение");
    }

    private JMenuBar initMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu backToSelectionMenuButton = new JMenu(OPEN);
        menuBar.add(backToSelectionMenuButton);
        backToSelectionMenuButton.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                selectionScreen.updateMenusList();
                selectionScreen.setVisible(true);
                if(dishesTable.getCellEditor() != null) {
                    dishesTable.getCellEditor().cancelCellEditing();
                }
                MenuViewerScreen.super.setVisible(false);
            }
            @Override
            public void menuDeselected(MenuEvent e) {
            }
            @Override
            public void menuCanceled(MenuEvent e) {
            }
        });

        return menuBar;
    }

    private JPanel initCategoryPanel() {
        selectionCategoryBox = getSelectionCategoryBox();
        selectionCategoryBox.addItemListener(e -> {
            if (selectionCategoryBox.getSelectedIndex() != -1) {
                deleteCategoryButton.setEnabled(true);
                editCategoryButton.setEnabled(true);
                addDishButton.setEnabled(true);
                updateTable((CategoryDTO) selectionCategoryBox.getSelectedItem());

            } else {
                deleteCategoryButton.setEnabled(false);
                editCategoryButton.setEnabled(false);
            }
            if(dishesTable.getCellEditor() != null) {
                dishesTable.getCellEditor().cancelCellEditing();
            }
        });

        JTextField categoryEditor = (JTextField) selectionCategoryBox.getEditor().getEditorComponent();
        categoryEditor.addActionListener(e -> {
            String categoryName = e.getActionCommand();
            if (categoryToEdit == null) {
                CategoryDTO newCategory = new CategoryDTO(categoryName);
                lastChosenCategory = newCategory;
                presenter.addCategory(newCategory, currentMenuName);
            } else {
                lastChosenCategory = new CategoryDTO(categoryName, categoryToEdit.getDishes());
                presenter.changeCategory(categoryName, categoryToEdit.getName(), currentMenuName);
            }

            selectionCategoryBox.setEditable(false);
        });
        categoryEditor.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                selectionCategoryBox.setEditable(false);
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
        addDishButton.addActionListener(e -> {
            dishesTableModel.insertEmptyRow();
            addDishButton.setEnabled(false);
        });

        deleteDishButton = getDeleteDishButton();
        deleteDishButton.addActionListener(e -> {
            String dishName = dishesTableModel.getDishAt(dishesTable.getSelectedRow()).getDishName();
            if(dishName.isEmpty()){
                dishesTableModel.deleteLast();
                addDishButton.setEnabled(true);
            }
            else {
                int choice = JOptionPane.showConfirmDialog(MenuViewerScreen.this, "Удалить блюдо " + dishName + "?", "Подтверждение удаления", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    String categoryName = ((CategoryDTO) selectionCategoryBox.getSelectedItem()).getName();
                    presenter.deleteDish(dishName, currentMenuName, categoryName);
                }
            }
        });

        dishesButtonPanel.setLayout(new BoxLayout(dishesButtonPanel, BoxLayout.X_AXIS));
        dishesButtonPanel.add(addDishButton);
        dishesButtonPanel.add(Box.createHorizontalStrut(10));
        dishesButtonPanel.add(deleteDishButton);

        return dishesButtonPanel;
    }

    public void setCurrentMenu(MenuDTO currentMenu, String currentMenuName) {
        this.currentMenuName = currentMenuName;
        selectionCategoryBox.setSelectedIndex(selectionCategoryBox.getItemCount() > 0 ? 0 : -1);
        updateMenu(currentMenu);
    }

    void updateMenu(MenuDTO currentMenu) {
        dishesTable.getSelectionModel().clearSelection();
        selectionCategoryBox.removeAllItems();
        List<CategoryDTO> sortedList = new ArrayList<>(currentMenu.getCategories());
        sortedList.sort(Comparator.comparing(CategoryDTO::getName));
        CategoryDTO categoryToSelect = null;
        for (CategoryDTO category : sortedList) {
            selectionCategoryBox.addItem(category);
            if(lastChosenCategory != null && category.getName().equals(lastChosenCategory.getName())){
                categoryToSelect = category;
            }
        }

        if (categoryToSelect != null) {
            selectionCategoryBox.setSelectedItem(categoryToSelect);
        }
        updateTable((CategoryDTO) selectionCategoryBox.getSelectedItem());
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
        dishesTable = new JTable() {
            @Override
            public void editingStopped(ChangeEvent e) {
                TableCellEditor editor = getCellEditor();
                if (editor != null) {
                    DishDTO editingDish = dishesTableModel.getDishAt(editingRow);
                    Object changedValue = editor.getCellEditorValue();
                    if (editingColumn == 0) {
                        if (changedValue.toString().equals("")) {
                            JOptionPane.showMessageDialog(MenuViewerScreen.this, "Имя не может быть пустым!");
                        } else if (!editingDish.getDishName().equals("")) {
                            String categoryName = ((CategoryDTO) selectionCategoryBox.getSelectedItem()).getName();
                            String oldName = editingDish.getDishName();
                            editingDish.setDishName(changedValue.toString());
                            lastChosenCategory = (CategoryDTO) selectionCategoryBox.getSelectedItem();
                            presenter.changeDish(editingDish, oldName, currentMenuName, categoryName);
                        } else {
                            setValueAt(changedValue, editingRow, editingColumn);
                        }
                    } else {
                        if (Double.parseDouble(changedValue.toString()) > 0.0) {
                            lastChosenCategory = (CategoryDTO) selectionCategoryBox.getSelectedItem();
                            if (editingDish.getCost() != 0.0) {
                                editingDish.setCost(Double.parseDouble(changedValue.toString()));
                                String categoryName = ((CategoryDTO) selectionCategoryBox.getSelectedItem()).getName();
                                String oldName = editingDish.getDishName();
                                lastChosenCategory = (CategoryDTO) selectionCategoryBox.getSelectedItem();
                                presenter.changeDish(editingDish, oldName, currentMenuName, categoryName);
                            } else {
                                editingDish.setCost(Double.parseDouble(changedValue.toString()));
                                String categoryName = ((CategoryDTO) selectionCategoryBox.getSelectedItem()).getName();
                                presenter.addDish(currentMenuName, editingDish, categoryName);
                                addDishButton.setEnabled(true);
                            }
                        } else {
                            JOptionPane.showMessageDialog(MenuViewerScreen.this, "Стоимость не может быть нулем или отрицательной!");
                        }
                    }
                    removeEditor();
                }
            }
        };
        dishesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        dishesTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        dishesTable.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        dishesTable.setRowHeight(20);
        dishesTableModel = new DishesTableModel();
        dishesTable.setModel(dishesTableModel);
        dishesTable.getSelectionModel().addListSelectionListener(e -> {
            if(dishesTable.getSelectionModel().isSelectionEmpty() || dishesTable.isEditing()){
                deleteDishButton.setEnabled(false);
            } else {
                deleteDishButton.setEnabled(true);
            }
        });

        return dishesTable;
    }

    private void updateTable(CategoryDTO category) {
        List<DishDTO> dishes = new ArrayList<>();
        if (category!=null) {
            dishes.addAll(category.getDishes());
            dishes.sort(Comparator.comparing(DishDTO::getDishName));
        }
        dishesTableModel.update(dishes);
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

    private JButton getDeleteDishButton() {
        if (deleteDishButton == null) {
            deleteDishButton = new JButton(REMOVE);
            deleteDishButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 22));
            setEnabled(false);
        }
        return deleteDishButton;
    }

    void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    private class DishesTableModel extends AbstractTableModel {
        private static final long serialVersionUID = 5898245907476971686L;

        private final String[] columnNames = {NAME_DISH, PRICE_DISH};
        private final List<DishDTO> dishes;

        private DishesTableModel() {
            this.dishes = new ArrayList<>();
        }

        void update(List<DishDTO> dishesToAdd) {
            dishes.clear();
            for (DishDTO dish : dishesToAdd) {
                dishes.add(dish);
            }

            fireTableDataChanged();
        }

        private void insertEmptyRow() {
            dishes.add(new DishDTO());

            fireTableRowsInserted(dishes.size() - 1, dishes.size());
        }

        private DishDTO getDishAt(int row) {
            return dishes.get(row);
        }

        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            switch (columnIndex) {
                case 0:
                    dishes.get(rowIndex).setDishName((String) aValue);
                    break;
                case 1:
                    dishes.get(rowIndex).setCost(Double.parseDouble((String) aValue));
                    break;
            }
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return columnIndex == 0 || !dishes.get(rowIndex).getDishName().equals("");
        }

        @Override
        public String getColumnName(int column) {
            return columnNames[column];
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

        void deleteLast() {
            dishes.remove(dishes.size() - 1);

            fireTableDataChanged();
        }
    }
}