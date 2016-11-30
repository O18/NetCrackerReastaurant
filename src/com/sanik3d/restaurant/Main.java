package com.sanik3d.restaurant;

import com.sanik3d.restaurant.controller.Controller;
import com.sanik3d.restaurant.presenter.Presenter;
import com.sanik3d.restaurant.view.View;

/**
 * Created by Александр on 06.11.2016.
 */
public class Main {
    private static View view;
    private static Presenter presenter;
    public static void main(String[] args) {
        view = new View();
        view.begin();
    }
}
