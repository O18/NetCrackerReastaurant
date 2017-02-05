package forms.viwer.view;

import forms.selection.view.MenuSelectionScreen;
import model.CategoryDTO;
import model.DishDTO;
import model.MenuDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

/**
 * Created by 1 on 27.12.2016.
 */
public class MenuViewerScreen extends JFrame {
    private static final String TITLE = "Работа с меню";
    private static final String OPEN = "Открыть";
    private static final String ADD = "+";
    private static final String REMOVE = "-";
    private static final String EDIT = "Изменить";
    private static final String SAVE_CHANGE = "V";
    private static final String REMOVE_CHANGE = "X";
    private static final String NAME_DISH = "Название блюда";
    private static final String PRICE_DISH = "Цена";

    private Vector<DishDTO> dataAboutDishes ;
    private Vector<String> columnHeader = new Vector<>(2);
    private static final long serialVersionUID = -9135268706317372751L;

    private JComboBox<CategoryDTO> selectionCategoryBox;
    private JButton addCategoryButton;
    private JButton deleteCategoryButton;
    private JButton editCategoryButton;
    private JTextField categoryNameField;
    private JButton saveChangeCategoriesButton;
    private JButton removeChangeCategoriesButton;
    private JTable dishesTable;
    private JButton addDishButton;
    private JButton removeDishButton;
    private JButton editDishButton;

    private DefaultTableModel dishesTableModel;
    private ViewerPresenter presenter;
    private MenuSelectionScreen selectionScreen;

    private String currentMenuName;
    private CategoryDTO categoryToEdit;
    private CategoryDTO currentCategory;

    public MenuViewerScreen() {
        super(TITLE);
        addCategoryButton = getAddCategoryButton();
        deleteCategoryButton = getDeleteCategoryButton();
        editCategoryButton = getEditCategoryButton();
        selectionCategoryBox = getSelectionCategoryBox();
        categoryNameField = getCategoryNameField();
        saveChangeCategoriesButton = getSaveChangeCategoriesButton();
        removeChangeCategoriesButton = getRemoveChangeCategoriesButton();
        addDishButton = getAddDishButton();
        removeDishButton = getRemoveDishButton();
        editDishButton = getEditDishButton();
        dishesTable = getDishesTable();
        //dishesTableModel = new DefaultTableModel(dataAboutDishes, COLUMN_HEADER);
        columnHeader.add(NAME_DISH);
        columnHeader.add(PRICE_DISH);

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
        categoryButtonsPanel.add(Box.createHorizontalGlue());
        categoryButtonsPanel.add(deleteCategoryButton);
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

        //обновление таблицы при перемене категории
        //selectionCategoryBox.addItemListener(e -> setDishesTable((CategoryDTO) selectionCategoryBox.getSelectedItem()));

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
                selectionCategoryBox.setSelectedIndex(-1);
                categoryToEdit = null;
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
        deleteCategoryButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(selectionCategoryBox.getSelectedIndex() != -1) {
                    presenter.deleteCategoryEvent( ((CategoryDTO)selectionCategoryBox.getSelectedItem()).getName(), currentMenuName);
                    selectionCategoryBox.setEditable(false);
                } else {
                    JOptionPane.showMessageDialog(MenuViewerScreen.this, "Не выбрана категория");
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
        editCategoryButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(selectionCategoryBox.getSelectedIndex() != -1){
                    selectionCategoryBox.setEditable(true);
                    categoryToEdit = (CategoryDTO) selectionCategoryBox.getSelectedItem();

                } else{
                    JOptionPane.showMessageDialog(MenuViewerScreen.this, "Не выбрана категория");
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

        selectionCategoryBox.addActionListener(e -> {
            if(e.getActionCommand().equals("comboBoxEdited")){
                if(categoryToEdit == null){
                    String newCategoryName = (String) selectionCategoryBox.getSelectedItem();
                    if(newCategoryName.equals("")){
                        JOptionPane.showMessageDialog(this, "Имя категории не может быть пустым");
                        selectionCategoryBox.setSelectedIndex(selectionCategoryBox.getItemCount() - 1);
                    } else {
                        CategoryDTO newCategory = new CategoryDTO(newCategoryName);
                        presenter.addCategory(newCategory, currentMenuName);
                    }
                }
                else {
                    if(selectionCategoryBox.getSelectedIndex() == -1) {
                        String newCategoryName = (String) selectionCategoryBox.getSelectedItem();
                        presenter.changeCategory(newCategoryName, categoryToEdit.getName(), currentMenuName);
                    }
                }
                selectionCategoryBox.setEditable(false);
            }
        });


        selectionCategoryBox.addItemListener(e -> {

        });

        //упакуем
        addDishButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //presenter.addDish(currentMenuName,selectionCategoryBox.getSelectedItem().toString());
                /*Vector newRow = new Vector();
                dishesTableModel.getDataVector().add(newRow);
                dishesTableModel.newRowsAdded(new TableModelEvent(dishesTableModel));*/
//todo
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
        removeDishButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //todo presenter.deleteDish(dishesTable.getSelectedRow(),currentMenuName,selectionCategoryBox.getSelectedItem().toString());
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
        editDishButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //todo
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

        //упакуем
        this.pack();
        this.setMinimumSize(getSize());
    }

    public void setCurrentMenu(MenuDTO currentMenu, String currentMenuName) {
        this.currentMenuName = currentMenuName;
        updateMenu(currentMenu);
    }

    void updateMenu(MenuDTO currentMenu){
        selectionCategoryBox.removeAllItems();
        for (CategoryDTO category : currentMenu.getCategories()) {
            selectionCategoryBox.addItem(category);
        }
    }
    //изменение таблицы
    public void setDishesTable(CategoryDTO category){
        this.currentCategory = category;
        dataAboutDishes.removeAllElements();
        for (DishDTO dish : category.getDishes()) {
            dataAboutDishes.add(dish);
        }
        dishesTable.revalidate();
        /*this.dishesTableModel = new DefaultTableModel(dataAboutDishes,columnHeader);
        dishesTable.setModel(dishesTableModel);*/
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

    private JTextField getCategoryNameField() {
        if (categoryNameField == null) {
            categoryNameField = new JTextField(20);
            categoryNameField.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
            categoryNameField.setVisible(false);
        }
        return categoryNameField;
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
            dishesTable = new JTable(dishesTableModel);
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

    void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}