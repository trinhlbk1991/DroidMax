package com.icetea09.droidmax.model.event;

import com.icetea09.droidmax.rules.IRule;

import de.greenrobot.event.EventBus;

/**
 * Created by KhanhTrinh on 1/10/2016.
 */
public class SelectConditionEvent {
    private IRule mCondition;

    public static void fire(IRule condition) {
        SelectConditionEvent event = new SelectConditionEvent(condition);
        EventBus.getDefault().post(event);
    }

    public SelectConditionEvent(IRule rule) {
        mCondition = rule;
    }

    public IRule getRule() {
        return mCondition;
    }
}
