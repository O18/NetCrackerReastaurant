package com.sanik3d.restaurant.presenter;

import com.sanik3d.restaurant.exceptions.NotEnoughDataException;
import com.sanik3d.restaurant.model.Category;

/**
 * Created by Александр on 18.12.2016.
 */
class ShowCategoriesStrategy implements PresenterStrategy {
    private Presenter presenter;

    ShowCategoriesStrategy(Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void performAction(String[] actionArgs) throws NotEnoughDataException {
        if (presenter.getMenu().getCategories().isEmpty()) {
            showNoCategories();
        } else {
            showAllCategories();
        }
    }

    private void showAllCategories() {
        StringBuilder stringToPrint = new StringBuilder("      Категории");
        stringToPrint.append("\n--------------------");
        for (Category category : presenter.getMenu().getCategories())
            stringToPrint.append("\n").append(category.getName());
        stringToPrint.append("\n--------------------");
        presenter.getView().print(stringToPrint.toString());
    }

    private void showNoCategories() {
        presenter.getView().print("Ни одной категории не существует.");
    }
}
