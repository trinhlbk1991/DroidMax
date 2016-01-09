package com.icetea09.droidmax.model.event;

import com.icetea09.droidmax.model.Rule;

import de.greenrobot.event.EventBus;

/**
 * Created by Nam Nguyen on 09-Jan-16.
 */
public class AddRuleEvent {

    private Rule mRule;

    public static void fire(Rule rule) {
        AddRuleEvent event = new AddRuleEvent(rule);
        EventBus.getDefault().post(event);
    }

    public AddRuleEvent(Rule rule) {
        mRule = rule;
    }

    public Rule getRule(){
        return  mRule;
    }
}
