package com.icetea09.droidmax.model;

import android.text.TextUtils;
import android.util.Pair;

import com.icetea09.droidmax.actions.IAction;
import com.icetea09.droidmax.rules.IRule;
import com.icetea09.droidmax.utils.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by KhanhTrinh on 1/9/2016.
 */
public class Rule {

    public static final String ITEMS_SEPARATOR = "#";
    public static final String ARGS_SEPARATOR = "~";

    private String mId;
    private String mName;
    private String[] mCategories;
    private List<IRule> mConditions;
    private List<IAction> mActions;
    private int mNumOfPerformed;

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        this.mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String nam) {
        this.mName = nam;
    }

    public String[] getCategories() {
        return mCategories;
    }

    public String getCategoriesString() {
        return StringUtils.convertStringArrayToStringWithDelimiter(mCategories, ITEMS_SEPARATOR);
    }

    public void setCategories(String strCategories) {
        if (TextUtils.isEmpty(strCategories)) {
            mCategories = null;
            return;
        }
        mCategories = strCategories.split(ITEMS_SEPARATOR);
    }

    public List<IRule> getConditions() {
        return mConditions;
    }

    public String getConditionsString() {
        String result = "";
        for (IRule condition : mConditions) {
            result += condition.convertToString() + ITEMS_SEPARATOR;
        }

        if (mConditions != null && mConditions.size() > 0) {
            result = result.substring(0, result.length() - 1);
        }
        return result;
    }

    public void setConditions(List<IRule> conditions) {
        mConditions = conditions;
    }

    public void setConditions(String strConditions) {
        mConditions = new ArrayList<>();
        String[] arrConditions = strConditions.split(ITEMS_SEPARATOR);
        for (String str : arrConditions) {
            Pair<String, String[]> methodSignature = getClassNameAndArgsFromString(str);

            IRule condition = null;
            try {
                condition = RulesFactory.getInstance().getCondition(methodSignature.first, methodSignature.second);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (condition != null) {
                mConditions.add(condition);
            }
        }
    }

    public List<IAction> getActions() {
        return mActions;
    }

    public String getActionsString() {
        String result = "";
        for (IAction action : mActions) {
            result += action.convertToString() + ITEMS_SEPARATOR;
        }

        if (mActions != null && mActions.size() > 0) {
            result = result.substring(0, result.length() - 1);
        }
        return result;
    }


    public void setActions(List<IAction> actions) {
        mActions = actions;
    }

    public void setActions(String strActions) {
        mActions = new ArrayList<>();
        String[] arrActions = strActions.split(ITEMS_SEPARATOR);
        for (String str : arrActions) {
            Pair<String, String[]> methodSignature = getClassNameAndArgsFromString(str);

            IAction action = null;
            try {
                action = RulesFactory.getInstance().getAction(methodSignature.first, methodSignature.second);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (action != null) {
                mActions.add(action);
            }
        }
    }

    public int getNumOfPerformed() {
        return mNumOfPerformed;
    }

    public void setNumOfPerformed(int mNumOfPerformed) {
        this.mNumOfPerformed = mNumOfPerformed;
    }

    private Pair<String, String[]> getClassNameAndArgsFromString(String str) {
        String className = null;
        String[] args = null;

        String[] arrArgs = str.split(ARGS_SEPARATOR);
        if (arrArgs.length > 0) {
            className = arrArgs[0];
            if (arrArgs.length > 1) {
                args = Arrays.copyOfRange(arrArgs, 1, arrArgs.length);
            }
        }
        return new Pair<>(className, args);
    }
}
