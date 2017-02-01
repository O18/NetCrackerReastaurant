package forms.viwer.view;

import forms.creation.view.MenuCreationScreen;
import forms.selection.Main;
import forms.selection.view.MenuSelectionScreen;
import model.CategoryDTO;
import model.DishDTO;
import model.MenuDTO;
import sun.swing.table.DefaultTableCellHeaderRenderer;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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

    private String[][] dataAboutDishes = {{"Картофель фри","150"},{"Овощи","200"}};
    private static final String[] COLUMN_HEADER = {"Название блюда","Цена"};


    private ViewerPresenter presenter;
    private static final long serialVersionUID = -9135268706317372751L;

    private JButton backToSelectionMenuButton;
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

    public MenuViewerScreen(String menuName) {
        super(TITLE);
        backToSelectionMenuButton = getBackToSelectionMenuButton();
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


        this.setSize(700, 400);
        JPanel panel = new JPanel();
        this.add(panel);
        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        panel.setLayout(gbl);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.anchor = gbc.NORTHWEST;
        panel.add(backToSelectionMenuButton, gbc);
        gbc.gridy = 2;
        gbc.gridx = 0;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(10, 5, 0, 0);
        panel.add(selectionCategoryBox, gbc);
        gbc.gridwidth = 1;
        gbc.gridx = 5;
        panel.add(addCategoryButton, gbc);
        gbc.gridx = GridBagConstraints.RELATIVE;
        panel.add(removeCategoryButton, gbc);
        panel.add(editCategoryButton, gbc);
        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.gridwidth = 3;
        panel.add(addAndEditNameCategory, gbc);
        gbc.gridwidth = 1;
        gbc.gridx = GridBagConstraints.RELATIVE;
        panel.add(saveChangeCategoriesButton,gbc);
        panel.add(removeChangeCategoriesButton,gbc);
        gbc.gridy = 5;
        gbc.gridx = 0;
        gbc.gridwidth = 4;
        gbc.gridy = 4;
        panel.add(new JScrollPane(dishesTable),gbc);
        panel.add(dishesTable,gbc);
        gbc.gridy = 5;
        gbc.gridx = 5;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        panel.add(addDishButton,gbc);
        gbc.gridx = GridBagConstraints.RELATIVE;
        panel.add(removeDishButton,gbc);
        panel.add(editDishButton,gbc);
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
                addAndEditNameCategory.setVisible(true);
                saveChangeCategoriesButton.setVisible(true);
                removeChangeCategoriesButton.setVisible(true);
                addOrEdit = true;
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
                presenter.deleteCategoryEvent(selectionCategoryBox.getSelectedItem().toString(),currentMenu.toString());//todo (ниже)
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
                if(addOrEdit) {
                    presenter.addCategory(new CategoryDTO(addAndEditNameCategory.getText()),currentMenu.toString());//todo currentMenu.toString() не даст названия меню, а нужно именно оно!!!
                }
                else {
                    presenter.changeCategory(new CategoryDTO(addAndEditNameCategory.getText()),selectionCategoryBox.getSelectedItem().toString(),currentMenu.toString());//todo
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

    }

    public void setCurrentMenu(MenuDTO currentMenu) {
        this.currentMenu = currentMenu;
        for(CategoryDTO category : currentMenu.getCategories()){
            selectionCategoryBox.addItem(category);
        }
    }

    private JButton getBackToSelectionMenuButton() {
        if (backToSelectionMenuButton == null) {
            backToSelectionMenuButton = new JButton(OPEN);
            backToSelectionMenuButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        }
        return backToSelectionMenuButton;
    }

    private JComboBox<CategoryDTO> getSelectionCategoryBox() {
        if (selectionCategoryBox == null) {
            selectionCategoryBox = new JComboBox<CategoryDTO>();
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
        if(saveChangeCategoriesButton ==null) {
            saveChangeCategoriesButton = new JButton(SAVE_CHANGE);
            saveChangeCategoriesButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
            saveChangeCategoriesButton.setVisible(false);
        }
        return saveChangeCategoriesButton;
    }
    private JButton getRemoveChangeCategoriesButton() {
        if(removeChangeCategoriesButton==null) {
            removeChangeCategoriesButton = new JButton(REMOVE_CHANGE);
            removeChangeCategoriesButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
            removeChangeCategoriesButton.setVisible(false);
        }
        return removeChangeCategoriesButton;
    }
    private JTable getDishesTable() {
        if (dishesTable == null) {
            dishesTable = new JTable(dataAboutDishes,COLUMN_HEADER);
            dishesTable.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
            /*JTableHeader header = dishesTable.getTableHeader();
            header.setReorderingAllowed(false);
            header.setResizingAllowed(false);*/

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