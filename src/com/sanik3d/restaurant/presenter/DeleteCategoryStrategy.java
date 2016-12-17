package com.sanik3d.restaurant.presenter;

import com.sanik3d.restaurant.events.DeleteCategoryEvent;
import com.sanik3d.restaurant.exceptions.NotEnoughDataException;

/**
 * Created by Александр on 17.12.2016.
 */
class DeleteCategoryStrategy implements PresenterStrategy {
    @Override
    public void performAction(Presenter presenter, String[] actionArgs) throws NotEnoughDataException {
        if (actionArgs.length < 1)
            throw new NotEnoughDataException("Недостаточно данных. Введите название категории для её удаления.");

        String nameOfCategory = actionArgs[0];
        new DeleteCategoryEvent(nameOfCategory, new Callback() {
            @Override
            public void onSuccess() {
                presenter.getView().print("Удаление категории прошло успешно!");
            }

            @Override
            public void onFail(RuntimeException e) {
                presenter.getView().print(e.getMessage());
            }
        });
    }
}
