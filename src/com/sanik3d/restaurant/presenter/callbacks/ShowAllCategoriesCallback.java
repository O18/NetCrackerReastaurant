package com.sanik3d.restaurant.presenter.callbacks;

import com.sanik3d.restaurant.model.Category;

import java.util.Set;

/**
 * Created by 1 on 29.11.2016.
 */
public interface ShowAllCategoriesCallback {

    void onFail();

    void onSuccess(Set<Category> ts);
}
