package com.sanik3d.restaurant.presenter;

import com.sanik3d.restaurant.exceptions.NotEnoughDataException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Александр on 18.12.2016.
 */
public class ShowHelpStrategy implements PresenterStrategy {

    private static final String HELP_TXT = "help.txt";

    private Presenter presenter;

    ShowHelpStrategy(Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void performAction(String[] actionArgs) throws NotEnoughDataException {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(HELP_TXT));
            StringBuilder result = new StringBuilder();
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                result.append(currentLine).append('\n');
            }

            presenter.getView().print(result.toString());
            reader.close();
        } catch (IOException e) {
            presenter.getView().print("Не найден файл");
        }
    }
}
