package com.sanik3d.restaurant.presenter;

/**
 * Created by 1 on 28.11.2016.
 */
public interface Callback {
    void onSuccess();

    void onFail(RuntimeException e);
}
