package com.o18.restaurant.presenter;

import com.o18.restaurant.events.Event;
import com.o18.restaurant.events.SaveMenuEvent;
import com.o18.restaurant.exceptions.NotEnoughDataException;

/**
 * Created by Александр on 18.12.2016.
 */
class SaveMenuStrategy implements PresenterStrategy {
    private Presenter presenter;

    SaveMenuStrategy(Presenter presenter) {
        this.presenter = presenter;
    }
    @Override
    public void performAction(String[] actionArgs) throws NotEnoughDataException {
        if (actionArgs.length < 1)
            throw new NotEnoughDataException("Недостаточно данных. Введите имя файла");

        String fileName = actionArgs[0];
        Event eventToPost = new SaveMenuEvent(fileName, new Callback() {

            @Override
            public void onSuccess() {
                presenter.getView().print("Соханение меню прошло успешно!");
            }

            @Override
            public void onFail(RuntimeException e) {
                presenter.getView().print(e.getMessage());
            }
        });

        presenter.getEventBus().post(eventToPost);
    }
}
