package com.icetea09.droidmax.rules;

import android.test.AndroidTestCase;

import com.icetea09.droidmax.rules.network.WifiEnableRule;

/**
 * Created by KhanhTrinh on 1/10/2016.
 */
public class WifiEnableTest extends AndroidTestCase {

    WifiEnableRule mRule;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mRule = new WifiEnableRule();
    }

    public void testIsSatisfiedWithNullContext() {
        mRule.setContext(null);
        assertFalse(mRule.isSatisfied());
    }

    public void testIsSatisfiedWithProperNetwork() {
        mRule.setContext(mContext);
        assertTrue(mRule.isSatisfied());
    }

    public void testGetRuleDescription() {
        assertEquals("Wifi turn on", mRule.getRuleDescription());
    }

    public void testConvertToString() {
        assertEquals(WifiEnableRule.TAG, mRule.convertToString());
    }

}
