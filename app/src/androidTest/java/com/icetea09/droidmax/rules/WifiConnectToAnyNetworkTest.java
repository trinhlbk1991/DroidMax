package com.icetea09.droidmax.rules;

import android.test.AndroidTestCase;

import com.icetea09.droidmax.rules.network.WifiConnectToAnyNetwork;

/**
 * Created by KhanhTrinh on 1/10/2016.
 */
public class WifiConnectToAnyNetworkTest extends AndroidTestCase {

    WifiConnectToAnyNetwork mRule;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mRule = new WifiConnectToAnyNetwork();
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
        assertEquals("Connect to any network", mRule.getRuleDescription());
    }

    public void testConvertToString() {
        assertEquals(WifiConnectToAnyNetwork.TAG, mRule.convertToString());
    }

}
