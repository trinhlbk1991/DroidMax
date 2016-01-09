package com.icetea09.droidmax.rules;

import android.test.AndroidTestCase;

import com.icetea09.droidmax.model.Rule;
import com.icetea09.droidmax.rules.network.WifiConnectToSpecificNetwork;

/**
 * Created by KhanhTrinh on 1/10/2016.
 */
public class WifiConnectToSpecificNetworkTest extends AndroidTestCase {

    WifiConnectToSpecificNetwork mRule;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mRule = new WifiConnectToSpecificNetwork("WiredSSID");
    }

    public void testIsSatisfiedWithNullContext() {
        mRule.setContext(null);
        assertFalse(mRule.isSatisfied());
    }

    public void testIsSatisfiedWithProperNetwork() {
        mRule = new WifiConnectToSpecificNetwork("\"WiredSSID\"");
        mRule.setContext(mContext);
        assertTrue(mRule.isSatisfied());
    }

    public void testGetRuleDescription() {
        assertEquals("Connect to network with name: WiredSSID", mRule.getRuleDescription());
    }

    public void testConvertToString() {
        assertEquals(WifiConnectToSpecificNetwork.TAG + Rule.ARGS_SEPARATOR + "WiredSSID", mRule.convertToString());
    }

}
