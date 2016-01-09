package com.icetea09.droidmax.model;

import android.util.Log;

import com.icetea09.droidmax.actions.IAction;
import com.icetea09.droidmax.rules.IRule;
import com.icetea09.droidmax.utils.StringUtils;

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
        Log.d(RulesFactory.class.getSimpleName(), rule + "-" + ((args != null && args.length > 0) ? StringUtils.convertStringArrayToStringWithDelimiter(args, "-") : ""));
        Class<?> ruleClass = Class.forName(rule);
        Constructor<?> constructor;

        if (args != null && args.length > 0) {
            Class<?>[] arrArgsClass = new Class<?>[args.length];
            for (int i = 0; i < arrArgsClass.length; i++) {
                arrArgsClass[i] = String.class;
            }
            constructor = ruleClass.getConstructor(arrArgsClass);
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
            Class<?>[] arrArgsClass = new Class<?>[args.length];
            for (int i = 0; i < arrArgsClass.length; i++) {
                arrArgsClass[i] = String.class;
            }
            constructor = ruleClass.getConstructor(arrArgsClass);
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
