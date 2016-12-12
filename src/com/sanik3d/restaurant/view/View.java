package com.sanik3d.restaurant.view;

import com.sanik3d.restaurant.presenter.Presenter;

import java.util.Scanner;

/**
 * Created by 1 on 13.11.2016.
 */
public class View {
    private Presenter presenter;

    public View() {
    }

    public void begin() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Введите команду:");
            String inString = sc.nextLine();
            presenter.decodingCommand(inString);
        }
    }

    public void print(String string) {
        System.out.println(string);
    }

    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }
}
