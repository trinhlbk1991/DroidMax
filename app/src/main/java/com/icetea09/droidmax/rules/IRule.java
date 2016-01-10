package com.icetea09.droidmax.rules;

/**
 * Created by KhanhTrinh on 1/9/2016.
 */
public interface IRule {

    boolean isSatisfied();

    String convertToString();

    int getIcon();

    String getRuleDescription();

    String getCategory();

}
