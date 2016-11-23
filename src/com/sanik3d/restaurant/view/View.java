package com.sanik3d.restaurant.view;

import com.sanik3d.restaurant.exceptions.ExceptionAddDish;
import com.sanik3d.restaurant.exceptions.ExceptionNameCategory;
import com.sanik3d.restaurant.exceptions.ExceptionNameDish;
import com.sanik3d.restaurant.exceptions.ExceptionPath;
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
                String str = sc.nextLine();
            try {
                presenter.Present(str);//ушли в presenter
            } catch (ExceptionPath exceptionPath) {
                exceptionPath.printStackTrace();
            } catch (ExceptionAddDish exceptionAddDish) {
                exceptionAddDish.printStackTrace();
            } catch (ExceptionNameCategory exceptionNameCategory) {
                exceptionNameCategory.printStackTrace();
            } catch (ExceptionNameDish exceptionNameDish) {
                exceptionNameDish.printStackTrace();
            }

        }
    }
}
