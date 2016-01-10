package com.icetea09.droidmax.rules;

import android.test.AndroidTestCase;

import com.icetea09.droidmax.rules.network.WifiDisableRule;

/**
 * Created by KhanhTrinh on 1/10/2016.
 */
public class WifiDisableTest extends AndroidTestCase {

    WifiDisableRule mRule;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mRule = new WifiDisableRule();
    }

    public void testIsSatisfiedWithNullContext() {
        mRule.setContext(null);
        assertFalse(mRule.isSatisfied());
    }

    public void testIsSatisfiedWithProperNetwork() {
        mRule.setContext(mContext);
        assertFalse(mRule.isSatisfied());
    }

    public void testGetRuleDescription() {
        assertEquals("Wifi turn off", mRule.getRuleDescription());
    }

    public void testConvertToString() {
        assertEquals(WifiDisableRule.TAG, mRule.convertToString());
    }

}
