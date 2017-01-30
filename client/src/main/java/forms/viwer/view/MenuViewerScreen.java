package forms.viwer.view;

import forms.creation.view.MenuCreationScreen;
import forms.selection.Main;
import forms.selection.view.MenuSelectionScreen;
import model.CategoryDTO;
import model.MenuDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
    private static final long serialVersionUID = -9135268706317372751L;

    private JButton backToSelectionMenuButton;
    private JComboBox<CategoryDTO> selectionCategoryBox;
    private JButton addCategoryButton;
    private JButton removeCategoryButton;
    private JButton editCategoryButton;
    private JTextField addAndEditNameCategory;
    private JButton saveChangeCategoriesButton;
    private JButton removeChangeCategoriesButton;

    private MenuSelectionScreen selectionScreen;

    private MenuDTO currentMenu;

    public MenuViewerScreen() {
        super(TITLE);
        backToSelectionMenuButton = getBackToSelectionMenuButton();
        addCategoryButton = getAddCategoryButton();
        removeCategoryButton = getRemoveCategoryButton();
        editCategoryButton = getEditCategoryButton();
        selectionCategoryBox = getSelectionCategoryBox();
        addAndEditNameCategory = getAddAndEditNameCategory();
        saveChangeCategoriesButton = getSaveChangeCategoriesButton();
        removeChangeCategoriesButton = getRemoveChangeCategoriesButton();

        this.setSize(700, 400);
        JPanel panel = new JPanel();
        this.add(panel);
        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        panel.setLayout(gbl);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.anchor = gbc.NORTHWEST;
        panel.add(backToSelectionMenuButton, gbc);
        gbc.gridy = 3;
        gbc.gridx = 5;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(10, 10, 0, 0);
        panel.add(selectionCategoryBox, gbc);
        gbc.gridwidth = 1;
        gbc.gridx = GridBagConstraints.RELATIVE;
        panel.add(addCategoryButton, gbc);
        panel.add(removeCategoryButton, gbc);
        panel.add(editCategoryButton, gbc);
        gbc.gridy = 4;
        gbc.gridx = 5;
        gbc.gridwidth = 3;
        panel.add(addAndEditNameCategory, gbc);
        gbc.gridwidth = 1;
        gbc.gridx = GridBagConstraints.RELATIVE;
        panel.add(saveChangeCategoriesButton,gbc);
        panel.add(removeChangeCategoriesButton,gbc);

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
                removeCategoryButton.setVisible(true);
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
                removeCategoryButton.setVisible(true);
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
                removeCategoryButton.setVisible(false);
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
                addAndEditNameCategory.setVisible(false);
                saveChangeCategoriesButton.setVisible(false);
                removeCategoryButton.setVisible(false);
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
        /*this.backToSelectionAction();
        this.visibleTextFieldAction(addAndEditNameCategory, saveChangeCategoriesButton,removeChangeCategoriesButton);
        this.saveChangeCategory(addAndEditNameCategory, saveChangeCategoriesButton,removeChangeCategoriesButton);
        this.removeChangeCategory(addAndEditNameCategory, saveChangeCategoriesButton,removeChangeCategoriesButton);*/
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

    public void setSelectionScreen(MenuSelectionScreen selectionScreen) {
        this.selectionScreen = selectionScreen;
    }

    private class BackToSelectionScreenAction implements ActionListener {
        private JFrame oldFrame;

        public BackToSelectionScreenAction(JFrame oldFrame) {
            this.oldFrame = oldFrame;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            oldFrame.setVisible(false);
            //MenuSelectionScreen.viwer(new String[]{});
        }
    }

    private void backToSelectionAction() {
        MenuViewerScreen.BackToSelectionScreenAction backToSelectionAction = new MenuViewerScreen.BackToSelectionScreenAction(this);
        backToSelectionMenuButton.addActionListener(backToSelectionAction);
    }

    /*private class VisibleTextFieldAction implements ActionListener {
        private JTextField fieldChanges;
        private JButton buttonSave;
        private JButton buttonRemove;

        public VisibleTextFieldAction(JTextField fieldChanges, JButton buttonSave, JButton buttonRemove) {
            this.fieldChanges = fieldChanges;
            this.buttonSave = buttonSave;
            this.buttonRemove = buttonRemove;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            fieldChanges.setVisible(true);
            buttonSave.setVisible(true);
            buttonRemove.setVisible(true);
        }
    }

    private void visibleTextFieldAction(JTextField fieldChanges, JButton buttonSave, JButton buttonRemove) {
        MenuViewerScreen.VisibleTextFieldAction visibleTextFieldAction = new MenuViewerScreen.VisibleTextFieldAction(fieldChanges,buttonSave,buttonRemove);
        addCategoryButton.addActionListener(visibleTextFieldAction);
        editCategoryButton.addActionListener(visibleTextFieldAction);
    }
    private class SaveChangeCategory implements ActionListener {
        private JTextField fieldChanges;
        private JButton buttonSave;
        private JButton buttonRemove;

        public SaveChangeCategory(JTextField fieldChanges, JButton buttonSave, JButton buttonRemove) {
            this.fieldChanges = fieldChanges;
            this.buttonSave = buttonSave;
            this.buttonRemove = buttonRemove;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            fieldChanges.setVisible(false);
            buttonSave.setVisible(false);
            buttonRemove.setVisible(false);//добавить реализацию
        }
    }

    private void saveChangeCategory(JTextField fieldChanges, JButton buttonSave, JButton buttonRemove) {
        MenuViewerScreen.SaveChangeCategory visibleTextFieldAction = new MenuViewerScreen.SaveChangeCategory(fieldChanges,buttonSave,buttonRemove);
        saveChangeCategoriesButton.addActionListener(visibleTextFieldAction);
    }
    private class RemoveChangeCategory implements ActionListener {
        private JTextField fieldChanges;
        private JButton buttonSave;
        private JButton buttonRemove;

        public RemoveChangeCategory(JTextField fieldChanges, JButton buttonSave, JButton buttonRemove) {
            this.fieldChanges = fieldChanges;
            this.buttonSave = buttonSave;
            this.buttonRemove = buttonRemove;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            fieldChanges.setVisible(false);
            buttonSave.setVisible(false);
            buttonRemove.setVisible(false);//добавить реализацию
        }
    }

    private void removeChangeCategory(JTextField fieldChanges, JButton buttonSave, JButton buttonRemove) {
        MenuViewerScreen.RemoveChangeCategory visibleTextFieldAction = new MenuViewerScreen.RemoveChangeCategory(fieldChanges,buttonSave,buttonRemove);
        removeChangeCategoriesButton.addActionListener(visibleTextFieldAction);
    }*/

}