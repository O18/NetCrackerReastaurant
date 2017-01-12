package com.o18.restaurant.view;

import com.o18.restaurant.presenter.Presenter;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

/**
 * Created by 1 on 13.11.2016.
 */
public class View {//TODO
    private Presenter presenter;

    public View() {
    }

    public void begin() throws UnsupportedEncodingException {//TODO remove checked
        String encoding = System.getProperty("console.encoding", "cp866");
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
