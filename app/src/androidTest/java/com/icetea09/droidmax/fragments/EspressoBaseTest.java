package com.icetea09.droidmax.fragments;

import android.support.test.rule.ActivityTestRule;

import com.icetea09.droidmax.MainActivity;
import com.icetea09.droidmax.database.RulesDataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;

/**
 * Created by KhanhTrinh on 1/10/2016.
 */
public class EspressoBaseTest {

    RulesDataSource mRulesDS;

    @Before
    public void setUp() {
        mRulesDS = new RulesDataSource(mMainActivityRule.getActivity());
    }

    @Rule
    public ActivityTestRule<MainActivity> mMainActivityRule = new ActivityTestRule<>(MainActivity.class);

    @After
    public void tearDown() {
        mRulesDS.deleteAllRules();
    }

    protected void wait(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected void wait(Condition condition, int time) {
        while (!condition.isSatisfied()) {
            wait(time);
        }
    }

    public interface Condition {
        boolean isSatisfied();
    }
}
