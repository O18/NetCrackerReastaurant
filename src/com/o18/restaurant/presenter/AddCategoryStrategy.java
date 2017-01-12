package com.o18.restaurant.presenter;

import com.o18.restaurant.events.AddCategoryEvent;
import com.o18.restaurant.events.Event;
import com.o18.restaurant.exceptions.NotEnoughDataException;

/**
 * Created by Александр on 17.12.2016.
 */
class AddCategoryStrategy implements PresenterStrategy {
    private Presenter presenter;

    AddCategoryStrategy(Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void performAction(String[] actionArgs) throws NotEnoughDataException {
        if (actionArgs.length < 1) {
            throw new NotEnoughDataException("Недостаточно данных. Введите название категории для её добавления.");
        }
        ArgumentParser parser = new ArgumentParser(actionArgs);

        String categoryName = parser.getNextString();
        Event eventToPost = new AddCategoryEvent(categoryName, new Callback() {
            @Override
            public void onSuccess() {
                presenter.getView().print("Добавление категории прошло успешно!");
            }

            @Override
            public void onFail(RuntimeException e) {
                presenter.getView().print(e.getMessage());
            }
        });

        presenter.getEventBus().post(eventToPost);
    }
}
