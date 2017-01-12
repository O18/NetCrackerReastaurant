package com.o18.restaurant.presenter;

import com.o18.restaurant.exceptions.NotEnoughDataException;
import com.o18.restaurant.model.Dish;

/**
 * Created by Александр on 18.12.2016.
 */
class ShowDishesStrategy implements PresenterStrategy {
    private Presenter presenter;

    ShowDishesStrategy(Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void performAction(String[] actionArgs) throws NotEnoughDataException {
        if (presenter.getMenu().getDishes().isEmpty()) {
            showNoDishes();
        } else {
            showAllDishes();
        }
    }

    private void showAllDishes() {
        StringBuilder stringToPrint = new StringBuilder("      Блюда      ");
        stringToPrint.append("\n------------------------");
        for (Dish dish : presenter.getMenu().getDishes()) {
            stringToPrint.append("\n").append(dish.getName()).append(" ").
                    append(dish.getCost()).append(" ").append(dish.getCategoryName());
        }
        stringToPrint.append("\n------------------------");
        presenter.getView().print(stringToPrint.toString());
    }

    private void showNoDishes() {
        presenter.getView().print("Ни одного блюда не существует.");
    }
}
