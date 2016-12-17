package com.sanik3d.restaurant.presenter;

import com.sanik3d.restaurant.events.AddDishEvent;
import com.sanik3d.restaurant.events.Event;
import com.sanik3d.restaurant.exceptions.NotEnoughDataException;

/**
 * Created by Александр on 17.12.2016.
 */
class AddDishStrategy implements PresenterStrategy {
    @Override
    public void performAction(Presenter presenter, String[] actionArgs) throws NotEnoughDataException {
        if (actionArgs.length < 3)
            throw new NotEnoughDataException("Недостаточно данных. Введите название блюда, стоимость " +
                    "и название категории для добавлеия блюда. Категория уже должна сущестовать.");
        String nameOfDish = actionArgs[0];
        double priceOfDish = tryParseDouble(actionArgs[1]);
        String nameOfCategory = actionArgs[2];

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

    private double tryParseDouble(String s) throws NotEnoughDataException {
        try {
            return Double.parseDouble(s);
        } catch (NumberFormatException e) {
            throw new NotEnoughDataException("Неверный второй аргумент при добавлении блюда. Ожидается число");
        }
    }
}
