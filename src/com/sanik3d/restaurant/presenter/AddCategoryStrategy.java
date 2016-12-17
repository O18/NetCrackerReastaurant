package com.sanik3d.restaurant.presenter;

import com.sanik3d.restaurant.events.AddCategoryEvent;
import com.sanik3d.restaurant.events.Event;
import com.sanik3d.restaurant.exceptions.NotEnoughDataException;

/**
 * Created by Александр on 17.12.2016.
 */
class AddCategoryStrategy implements PresenterStrategy {
    @Override
    public void performAction(Presenter presenter, String[] actionArgs) throws NotEnoughDataException {
        if (actionArgs.length < 1) {
            throw new NotEnoughDataException("Недостаточно данных. Введите название категории для её добавления.");
        }

        String categoryName = actionArgs[0];
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
