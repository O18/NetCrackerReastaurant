package com.o18.restaurant;

import com.o18.restaurant.model.Category;

import javax.swing.*;
import java.awt.*;

/**
 * Created by 1 on 27.12.2016.
 */
public class MenuViewScreen extends JFrame {
    private static final String TITLE = "Работа с меню";
    private static final String OPEN = "Открыть";
    private static final String ADD = "+";
    private static final String REMOVE = "-";
    private static final String EDIT = "Изменить";
    private JButton backToSelectionMenuButton;
    private JComboBox<Category> selectionCategoryBox;
    private JButton addCategoryButton;
    private JButton removeCategoryButton;
    private JButton editCategoryButton;


    public MenuViewScreen () {
        super(TITLE);
        this.setSize(700,400);
        //MenuSelectionScreen mss = new MenuSelectionScreen();
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
        //gbl.setConstraints(getBackToSelectionMenuButton(),gbc);
        panel.add(getBackToSelectionMenuButton(),gbc);
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        panel.add(getSelectionCategoryBox(),gbc);
        gbc.gridwidth = 1;
        gbc.gridx = GridBagConstraints.RELATIVE;
        panel.add(getAddCategoryButton(),gbc);
        panel.add(getRemoveCategoryButton(),gbc);
        panel.add(getEditCategoryButton(),gbc);
    }
    private JButton getBackToSelectionMenuButton() {
        if(backToSelectionMenuButton==null) {
            backToSelectionMenuButton = new JButton(OPEN);
            backToSelectionMenuButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        }
        return backToSelectionMenuButton;
    }
    private JComboBox<Category> getSelectionCategoryBox() {
        if(selectionCategoryBox == null) {
            Category[] arr = new Category[]{new Category("Мясо"),new Category("Рыба"), new Category("Птица")};
            selectionCategoryBox = new JComboBox<>(arr);
            selectionCategoryBox.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        }
        return selectionCategoryBox;
    }
    private JButton getAddCategoryButton() {
        if(addCategoryButton==null) {
            addCategoryButton = new JButton(ADD);
            addCategoryButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 22));
        }
        return addCategoryButton;
    }
    private JButton getRemoveCategoryButton() {
        if(removeCategoryButton==null) {
            removeCategoryButton = new JButton(REMOVE);
            removeCategoryButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 22));
        }
        return removeCategoryButton;
    }
    private JButton getEditCategoryButton() {
        if(editCategoryButton==null) {
            editCategoryButton = new JButton(EDIT);
            editCategoryButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        }
        return editCategoryButton;
    }
}
