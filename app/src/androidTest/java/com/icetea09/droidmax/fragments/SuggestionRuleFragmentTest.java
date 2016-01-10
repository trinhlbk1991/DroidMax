package com.icetea09.droidmax.fragments;

import com.icetea09.droidmax.R;

import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by KhanhTrinh on 1/10/2016.
 */
public class SuggestionRuleFragmentTest extends EspressoBaseTest {

    @Test
    public void testShouldShowRecommendRules() {
        onView(withId(R.id.viewPagerMainRule)).perform(swipeLeft());
        onView(withText("Go Home")).check(matches(isDisplayed()));
        onView(withText("Low Battery")).check(matches(isDisplayed()));
        onView(withText("It will be rainy")).check(matches(isDisplayed()));
    }

    @Test
    public void testAddRecommendRule() {
        onView(withId(R.id.viewPagerMainRule)).perform(swipeLeft());
        onView(withText("Go Home")).check(matches(isDisplayed()));
        onView(withText("Add 0")).perform(click());
        //onView(withText("Delete 0")).check(matches(isDisplayed()));
        wait(1000);
    }

}
