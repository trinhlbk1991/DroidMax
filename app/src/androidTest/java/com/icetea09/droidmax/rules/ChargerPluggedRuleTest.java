package com.icetea09.droidmax.rules;

import android.content.Intent;
import android.test.AndroidTestCase;

import com.icetea09.droidmax.rules.battery.ChargerPluggedRule;

/**
 * Created by KhanhTrinh on 1/10/2016.
 */
public class ChargerPluggedRuleTest extends AndroidTestCase {

    ChargerPluggedRule mRule;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mRule = new ChargerPluggedRule();
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_POWER_CONNECTED);
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
        assertEquals("Charger was plugged", mRule.getRuleDescription());
    }

    public void testConvertToString() {
        assertEquals(ChargerPluggedRule.TAG, mRule.convertToString());
    }

}
