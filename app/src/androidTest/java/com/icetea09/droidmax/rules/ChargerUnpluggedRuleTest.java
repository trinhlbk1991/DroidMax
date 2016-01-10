package com.icetea09.droidmax.rules;

import android.content.Intent;
import android.test.AndroidTestCase;

import com.icetea09.droidmax.rules.battery.ChargerUnpluggedRule;

/**
 * Created by KhanhTrinh on 1/10/2016.
 */
public class ChargerUnpluggedRuleTest extends AndroidTestCase {

    ChargerUnpluggedRule mRule;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mRule = new ChargerUnpluggedRule();
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_POWER_DISCONNECTED);
        mRule.setIntent(intent);
    }

    public void testIsSatisfiedWithNullIntent() {
        mRule.setIntent(null);
        assertFalse(mRule.isSatisfied());
    }

    public void testIsSatisfiedWithProperIntent() {
        assertTrue(mRule.isSatisfied());
    }

    public void testGetRuleDescription() {
        assertEquals("Charger was unplugged", mRule.getRuleDescription());
    }

    public void testConvertToString() {
        assertEquals(ChargerUnpluggedRule.TAG, mRule.convertToString());
    }

}
