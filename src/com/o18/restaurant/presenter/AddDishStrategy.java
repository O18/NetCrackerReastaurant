package com.o18.restaurant.presenter;

import com.o18.restaurant.events.AddDishEvent;
import com.o18.restaurant.events.Event;
import com.o18.restaurant.exceptions.NotEnoughDataException;

/**
 * Created by Александр on 17.12.2016.
 */
class AddDishStrategy implements PresenterStrategy {
    private Presenter presenter;

    AddDishStrategy(Presenter presenter) {
        this.presenter = presenter;
    }
    @Override
    public void performAction(String[] actionArgs) throws NotEnoughDataException {
        if (actionArgs.length < 3)
            throw new NotEnoughDataException("Недостаточно данных. Введите название блюда, стоимость " +
                    "и название категории для добавлеия блюда.");
        ArgumentParser parser = new ArgumentParser(actionArgs);

        String nameOfDish = parser.getNextStringWithoutNumbers();
        double priceOfDish = parser.getNextDouble();
        String nameOfCategory = parser.getNextString();

        Event eventToPost = new AddDishEvent(nameOfDish, priceOfDish, nameOfCategory, new Callback() {
            @Override
            public void onSuccess() {
                presenter.getView().print("Добавление блюда прошло успешно!");
            }

            @Override
            public void onFail(RuntimeException e) {
                presenter.getView().print(e.getMessage());
            }
        });

        presenter.getEventBus().post(eventToPost);
    }

    private String getDishName(String[] actionArgs) {
        StringBuilder result = new StringBuilder();
        int i = 0;
        while (i < actionArgs.length && !isNumber(actionArgs[i])) {
            result.append(actionArgs[i]);
            i++;
        }

        return result.toString();
    }

    private boolean isNumber(String s) {
        return s.matches("(-? [0-9]+ (\\.[0-9]+)?)+");
    }

    private double tryParseDouble(String s) throws NotEnoughDataException {
        try {
            double result = Double.parseDouble(s);
            if (result < 0)
                throw new NotEnoughDataException("Неверный второй аргумент при добавлении блюда." +
                        " Второй аргумент должен быть неотрицательным числом");

            return result;
        } catch (NumberFormatException e) {
            throw new NotEnoughDataException("Неверный второй аргумент при добавлении блюда." +
                    " Второй аргумент должен быть числом");
        }
    }
}
