package com.sanik3d.restaurant.presenter;

import com.sanik3d.restaurant.events.DeleteDishEvent;
import com.sanik3d.restaurant.events.Event;
import com.sanik3d.restaurant.exceptions.NotEnoughDataException;

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
