package com.sanik3d.restaurant.strategy;

import com.sanik3d.restaurant.events.Event;
import com.sanik3d.restaurant.events.HelpShowEvent;
import com.sanik3d.restaurant.presenter.callbacks.ShowHelpCallback;
import com.sanik3d.restaurant.view.View;

/**
 * Created by Александр on 05.12.2016.
 */
public class HelpStrategy implements Strategy {

    private View view;
    public HelpStrategy(View view) {
        this.view = view;
    }

    @Override
    public Event createEvent(String[] commandString) {
        return new HelpShowEvent(new ShowHelpCallback() {
            @Override
            public void onSuccess(String helpInfo) {
                view.print(helpInfo);
            }

            @Override
            public void onFail(RuntimeException e) {
                view.print(e.getMessage());
            }
        });
    }
}
