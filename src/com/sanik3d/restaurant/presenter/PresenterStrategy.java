package com.sanik3d.restaurant.presenter;

import com.sanik3d.restaurant.exceptions.NotEnoughDataException;

/**
 * Created by Александр on 17.12.2016.
 */
interface PresenterStrategy {

    void performAction(String[] actionArgs) throws NotEnoughDataException;
}
