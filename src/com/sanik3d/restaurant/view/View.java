package com.sanik3d.restaurant.view;

import com.sanik3d.restaurant.exceptions.NotEnoughtDataException;
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
        Scanner sc = new Scanner(System.in);//TODO: пересоздание Scanner
        while (true) {
            System.out.println("Введите команду:");
            String inString = sc.nextLine();
            try {
                presenter.decodingCommand(inString);//TODO: переделать название
                //ушли в presenter
            } catch (NotEnoughtDataException e) {
                e.printStackTrace();
            }//TODO: убрать try-catch


        }
    }

    public void print(String string) {
        System.out.println(string);
    }

    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }
}
