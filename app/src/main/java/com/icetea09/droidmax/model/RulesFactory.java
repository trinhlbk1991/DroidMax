package com.icetea09.droidmax.model;

import com.icetea09.droidmax.actions.IAction;
import com.icetea09.droidmax.rules.IRule;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by KhanhTrinh on 1/9/2016.
 */
public class RulesFactory {

    private static RulesFactory sInstance;

    public static synchronized RulesFactory getInstance() {
        if (sInstance == null) {
            sInstance = new RulesFactory();
        }
        return sInstance;
    }

    public IRule getCondition(String rule, String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> ruleClass = Class.forName(rule);
        Constructor<?> constructor;

        if (args != null && args.length > 0) {
            constructor = ruleClass.getConstructor(String.class);
            Object[] arguments = new Object[args.length];
            for (int i = 0; i < arguments.length; i++) {
                arguments[i] = args[i];
            }
            return (IRule) constructor.newInstance(arguments);
        } else {
            constructor = ruleClass.getConstructor();
            return (IRule) constructor.newInstance();
        }
    }

    public IAction getAction(String rule, String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> ruleClass = Class.forName(rule);
        Constructor<?> constructor;

        if (args != null && args.length > 0) {
            constructor = ruleClass.getConstructor(String.class);
            Object[] arguments = new Object[args.length];
            for (int i = 0; i < arguments.length; i++) {
                arguments[i] = args[i];
            }
            return (IAction) constructor.newInstance(arguments);
        } else {
            constructor = ruleClass.getConstructor();
            return (IAction) constructor.newInstance();
        }
    }

}
