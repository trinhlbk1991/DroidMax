package com.icetea09.droidmax.fragments;

import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.icetea09.droidmax.R;

import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeUp;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

/**
 * Created by KhanhTrinh on 1/10/2016.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class AddNewRuleFragmentTest extends EspressoBaseTest {

    @Test
    public void testShouldNavigateToAddRuleScreenWhenClickedOnFab() {
        onView(withId(R.id.fab_add_rule)).perform(click());
        onView(withId(R.id.et_rule_name)).check(matches(isDisplayed()));
        onView(withText("Conditions")).check(matches(isDisplayed()));
        onView(withText("Actions")).check(matches(isDisplayed()));
        onView(withText("Add Rule")).check(matches(isDisplayed()));
    }

    @Test
    public void testShouldShowErrorWhenEmptyName() {
        onView(withId(R.id.fab_add_rule)).perform(click());
        onView(withId(R.id.et_rule_name)).check(matches(isDisplayed()));
        onView(withText("Add Rule")).check(matches(isDisplayed())).perform(click());
        onView(withText("Rule name must be not empty"))
                .inRoot(withDecorView(not(is(mMainActivityRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    @Test
    public void testShouldShowErrorWhenSelectNoCondition() {
        onView(withId(R.id.fab_add_rule)).perform(click());
        onView(withId(R.id.et_rule_name)).check(matches(isDisplayed())).perform(typeText("Hello From Kai"));
        onView(withText("Add Rule")).check(matches(isDisplayed())).perform(click());
        onView(withText("Must select at least one condition"))
                .inRoot(withDecorView(not(is(mMainActivityRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    @Test
    public void testShouldShowErrorWhenSelectNoAction() {
        onView(withId(R.id.fab_add_rule)).perform(click());
        onView(withId(R.id.et_rule_name)).check(matches(isDisplayed())).perform(typeText("Hello From Kai"));
        onView(withText("Add Condition")).check(matches(isDisplayed())).perform(click());
        onView(withText("Battery")).check(matches(isDisplayed())).perform(click());
        onView(withText("Charger was plugged")).check(matches(isDisplayed())).perform(click());
        onView(withText("Add Rule")).check(matches(isDisplayed())).perform(click());
        onView(withText("Must select at least one action"))
                .inRoot(withDecorView(not(is(mMainActivityRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    @Test
    public void testShouldAddNewRuleWhenEnterEnoughInfo() {
        onView(withId(R.id.fab_add_rule)).perform(click());
        onView(withId(R.id.et_rule_name)).check(matches(isDisplayed())).perform(typeText("Hello From Kai"));
        onView(withText("Add Condition")).check(matches(isDisplayed())).perform(click());
        onView(withText("Battery")).check(matches(isDisplayed())).perform(click());
        onView(withText("Charger was plugged")).check(matches(isDisplayed())).perform(click());

        onView(withId(R.id.scrollView)).perform(swipeUp());

        onView(withText("Add Action")).check(matches(isDisplayed())).perform(click());
        onView(withText("Bluetooth")).check(matches(isDisplayed())).perform(click());
        onView(withText("Turn on the bluetooth")).check(matches(isDisplayed())).perform(click());
        onView(withText("Add Rule")).check(matches(isDisplayed())).perform(click());
        onView(withText("Added new rule successfully"))
                .inRoot(withDecorView(not(is(mMainActivityRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

}
