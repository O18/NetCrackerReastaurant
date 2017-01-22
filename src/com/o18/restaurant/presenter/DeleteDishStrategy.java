package com.o18.restaurant.presenter;

import com.o18.restaurant.events.DeleteDishEvent;
import com.o18.restaurant.eventbus.Event;
import com.o18.restaurant.exceptions.NotEnoughDataException;

/**
 * Created by Александр on 17.12.2016.
 */
class DeleteDishStrategy implements PresenterStrategy {
    private Presenter presenter;

    DeleteDishStrategy(Presenter presenter) {
        this.presenter = presenter;
    }
    @Override
    public void performAction(String[] actionArgs) throws NotEnoughDataException {
        if (actionArgs.length < 1)
            throw new NotEnoughDataException("Недостаточно данных. Введите название блюда для его удаления.");

        ArgumentParser parser = new ArgumentParser(actionArgs);
        String nameOfDish = parser.getNextString();

        Event eventToPost = new DeleteDishEvent(nameOfDish, new Callback() {
            @Override
            public void onSuccess() {
                presenter.getView().print("Удаление блюда прошло успешно!");
            }

            @Override
            public void onFail(RuntimeException e) {
                presenter.getView().print(e.getMessage());
            }
        });

        presenter.getEventBus().post(eventToPost);
    }
}
