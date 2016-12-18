package com.sanik3d.restaurant.view;

import com.sanik3d.restaurant.presenter.Presenter;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

/**
 * Created by 1 on 13.11.2016.
 */
public class View {
    private Presenter presenter;

    public View() {
    }

    public void begin() throws UnsupportedEncodingException {
        String encoding = System.getProperty("console.encoding", "utf-8");
        Scanner sc = new Scanner(System.in, encoding);
        PrintStream out = new PrintStream(System.out, true, encoding);
        while (true) {
            out.println("Введите команду:");
            String inString = sc.nextLine();
            presenter.decodeCommand(inString);
        }
    }

    public void print(String string) {
        System.out.println(string);
    }

    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }
}
