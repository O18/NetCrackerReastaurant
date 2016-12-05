package com.sanik3d.restaurant.presenter.callbacks;

/**
 * Created by Александр on 05.12.2016.
 */
public interface ShowHelpCallback  {

    void onSuccess(String helpInfo);

    void onFail(RuntimeException e);
}
