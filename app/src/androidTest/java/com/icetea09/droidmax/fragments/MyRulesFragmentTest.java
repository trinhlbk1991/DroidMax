package com.icetea09.droidmax.fragments;

import android.support.test.espresso.Espresso;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.icetea09.droidmax.R;
import com.icetea09.droidmax.model.Rule;
import com.icetea09.droidmax.rules.battery.BatteryRule;
import com.icetea09.droidmax.rules.battery.ChargerPluggedRule;
import com.icetea09.droidmax.rules.battery.LowBatteryRule;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by KhanhTrinh on 1/10/2016.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class MyRulesFragmentTest extends EspressoBaseTest {

    @Test
    public void testShouldShowMessageWhenThereIsNoRules() {
        onView(withText("Create some rules by your own or pick some suggested by us!")).check(matches(isDisplayed()));
    }

    @Test
    public void testShouldDisplayAllRulesCreatedByUser() {
        List<Rule> rules = createDummyRules();
        for (Rule rule : rules) {
            mRulesDS.addNewRule(rule);
        }
        onView(withId(R.id.fab_add_rule)).perform(click());
        Espresso.pressBack();

        onView(withText("Test rule 1")).check(matches(isDisplayed()));
        onView(withText("Test rule 2")).check(matches(isDisplayed()));
    }

    @Test
    public void testShouldDeleteItemWhenClickedOnDeleteButton() {
        Rule rule = createDummyRule("Test rule 1", 2, BatteryRule.TAG + "#" + "WifiRule");
        mRulesDS.addNewRule(rule);
        onView(withId(R.id.fab_add_rule)).perform(click());
        Espresso.pressBack();

        onView(withText("Delete 0")).perform(click());
        onView(withText("Create some rules by your own or pick some suggested by us!")).check(matches(isDisplayed()));
    }

    private Rule createDummyRule(String name, int id, String category) {
        String strConditions = new ChargerPluggedRule().convertToString() + Rule.ITEMS_SEPARATOR + new LowBatteryRule("15").convertToString();
        Rule rule = new Rule();
        rule.setId(String.valueOf(id));
        rule.setName(name);
        rule.setCategories(category);
        rule.setConditions(strConditions);
        rule.setActions("");
        rule.setNumOfPerformed(0);
        return rule;
    }

    private List<Rule> createDummyRules() {
        List<Rule> rules = new ArrayList<>();
        rules.add(createDummyRule("Test rule 1", 2, BatteryRule.TAG + "#" + "WifiRule"));
        rules.add(createDummyRule("Test rule 2", 3, "WifiRule#BluetoothRule"));
        return rules;
    }

}
