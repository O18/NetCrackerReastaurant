package com.sanik3d.restaurant.presenter.callbacks;

/**
 * Created by 1 on 28.11.2016.
 */
public interface AddCategoryCallback {
    void onSuccess();

    void onFailCategoryAlreadyExists();

    void onFail(RuntimeException e);
}
