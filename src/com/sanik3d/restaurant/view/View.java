package com.sanik3d.restaurant.view;

import com.sanik3d.restaurant.exceptions.NotEnoughDataException;
import com.sanik3d.restaurant.presenter.Presenter;

import java.util.Scanner;

/**
 * Created by 1 on 13.11.2016.
 */
public class View {
    private Presenter presenter;
    public View () {
        this.presenter = new Presenter();
    }
    public void begin() {
        while (true) {
                System.out.println("Введите команду:");
                Scanner sc = new Scanner(System.in);
                String inString = sc.nextLine();
            try {
                presenter.sendEvent(inString);//ушли в presenter
            }
            catch (NotEnoughDataException notEnoughDataException) {
                notEnoughDataException.printStackTrace();
            }

        }
    }
    public void print(String string) {
        System.out.println(string);
    }
}
