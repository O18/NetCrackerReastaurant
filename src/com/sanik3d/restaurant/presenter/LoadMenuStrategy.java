package com.sanik3d.restaurant.presenter;

import com.sanik3d.restaurant.events.Event;
import com.sanik3d.restaurant.events.LoadMenuInMemoryEvent;
import com.sanik3d.restaurant.exceptions.NotEnoughDataException;

/**
 * Created by Александр on 18.12.2016.
 */
class LoadMenuStrategy implements PresenterStrategy {
    private Presenter presenter;

    LoadMenuStrategy(Presenter presenter) {
        this.presenter = presenter;
    }
    @Override
    public void performAction(String[] actionArgs) throws NotEnoughDataException {
        if (actionArgs.length < 1)
            throw new NotEnoughDataException("Недостаточно данных. Введите имя загружаемого файла");

        String fileName = actionArgs[0];
        Event eventToPost = new LoadMenuInMemoryEvent(fileName, new Callback() {

            @Override
            public void onSuccess() {
                presenter.getView().print("Загрузка меню в память прошла успешно!");
            }

            @Override
            public void onFail(RuntimeException e) {
                presenter.getView().print(e.getMessage());
            }
        });

        presenter.getEventBus().post(eventToPost);
    }
}
