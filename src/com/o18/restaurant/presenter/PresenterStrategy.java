package com.o18.restaurant.presenter;

import com.o18.restaurant.exceptions.NotEnoughDataException;

/**
 * Created by Александр on 17.12.2016.
 */
interface PresenterStrategy {

    void performAction(String[] actionArgs) throws NotEnoughDataException;
}
