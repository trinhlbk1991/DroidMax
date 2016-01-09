package com.icetea09.droidmax.model.event;

import com.icetea09.droidmax.actions.IAction;

import de.greenrobot.event.EventBus;

/**
 * Created by KhanhTrinh on 1/10/2016.
 */
public class SelectActionEvent {
    private IAction mAction;

    public static void fire(IAction action) {
        SelectActionEvent event = new SelectActionEvent(action);
        EventBus.getDefault().post(event);
    }

    public SelectActionEvent(IAction action) {
        mAction = action;
    }

    public IAction getAction() {
        return mAction;
    }
}
