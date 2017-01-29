package form.main;

import form.select.view.MenuSelectionScreen;
import model.CategoryDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by 1 on 27.12.2016.
 */
public class MenuViewScreen extends JFrame {
    private static final String TITLE = "Работа с меню";
    private static final String OPEN = "Открыть";
    private static final String ADD = "+";
    private static final String REMOVE = "-";
    private static final String EDIT = "Изменить";
    private static final String SAVE_CHANGE = "V";
    private static final String REMOVE_CHANGE = "X";
    private JButton backToSelectionMenuButton;
    private JComboBox<CategoryDTO> selectionCategoryBox;
    private JButton addCategoryButton;
    private JButton removeCategoryButton;
    private JButton editCategoryButton;
    private JTextField addAndEditNameCategory;
    private JButton saveChangeCategotiesButton;
    private JButton removeChangeCategoriesButton;


    public MenuViewScreen() {
        super(TITLE);
        backToSelectionMenuButton = getBackToSelectionMenuButton();
        addCategoryButton = getAddCategoryButton();
        removeCategoryButton = getRemoveCategoryButton();
        editCategoryButton = getEditCategoryButton();
        selectionCategoryBox = getSelectionCategoryBox();
        addAndEditNameCategory = getAddAndEditNameCategory();
        saveChangeCategotiesButton = getSaveChangeCategoriesButton();
        removeChangeCategoriesButton = getRemoveChangeCategoriesButton();

        MenuSelectionScreen mss = new MenuSelectionScreen();
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
        panel.add(saveChangeCategotiesButton,gbc);
        //gbc.gridx = 9;
        panel.add(removeChangeCategoriesButton,gbc);
        this.backToSelectionAction();
        this.visibleTextFieldAction(addAndEditNameCategory,saveChangeCategotiesButton,removeChangeCategoriesButton);
        this.saveChangeCategory(addAndEditNameCategory,saveChangeCategotiesButton,removeChangeCategoriesButton);
        this.removeChangeCategory(addAndEditNameCategory,saveChangeCategotiesButton,removeChangeCategoriesButton);
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
            CategoryDTO[] arr = new CategoryDTO[]{new CategoryDTO("Мясо"), new CategoryDTO("Рыба"), new CategoryDTO("Птица")};
            selectionCategoryBox = new JComboBox<>(arr);
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
        if(saveChangeCategotiesButton ==null) {
            saveChangeCategotiesButton = new JButton(SAVE_CHANGE);
            saveChangeCategotiesButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
            saveChangeCategotiesButton.setVisible(false);
        }
        return saveChangeCategotiesButton;
    }
    private JButton getRemoveChangeCategoriesButton() {
        if(removeChangeCategoriesButton==null) {
            removeChangeCategoriesButton = new JButton(REMOVE_CHANGE);
            removeChangeCategoriesButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
            removeChangeCategoriesButton.setVisible(false);
        }
        return removeChangeCategoriesButton;
    }

    private class BackToSelectionScreenAction implements ActionListener {
        private JFrame oldFrame;

        public BackToSelectionScreenAction(JFrame oldFrame) {
            this.oldFrame = oldFrame;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            oldFrame.setVisible(false);
            //MenuSelectionScreen.main(new String[]{});
        }
    }

    private void backToSelectionAction() {
        MenuViewScreen.BackToSelectionScreenAction backToSelectionAction = new MenuViewScreen.BackToSelectionScreenAction(this);
        backToSelectionMenuButton.addActionListener(backToSelectionAction);
    }

    private class VisibleTextFieldAction implements ActionListener {
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
        MenuViewScreen.VisibleTextFieldAction visibleTextFieldAction = new MenuViewScreen.VisibleTextFieldAction(fieldChanges,buttonSave,buttonRemove);
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
        MenuViewScreen.SaveChangeCategory visibleTextFieldAction = new MenuViewScreen.SaveChangeCategory(fieldChanges,buttonSave,buttonRemove);
        saveChangeCategotiesButton.addActionListener(visibleTextFieldAction);
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
        MenuViewScreen.RemoveChangeCategory visibleTextFieldAction = new MenuViewScreen.RemoveChangeCategory(fieldChanges,buttonSave,buttonRemove);
        removeChangeCategoriesButton.addActionListener(visibleTextFieldAction);
    }

}
