package com.icetea09.droidmax.rules;

import android.content.Intent;
import android.os.BatteryManager;
import android.test.AndroidTestCase;

import com.icetea09.droidmax.model.Rule;
import com.icetea09.droidmax.rules.battery.LowBatteryRule;

/**
 * Created by KhanhTrinh on 1/10/2016.
 */
public class LowBatteryRuleTest extends AndroidTestCase {

    LowBatteryRule mRule;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mRule = new LowBatteryRule("15");
        Intent intent = new Intent();
        intent.putExtra(BatteryManager.EXTRA_LEVEL, 140);
        intent.putExtra(BatteryManager.EXTRA_SCALE, 100);
        mRule.setIntent(intent);
    }

    public void testIsSatisfiedWithNullIntent() {
        mRule.setIntent(null);
        assertFalse(mRule.isSatisfied());
    }

    public void testIsSatisfiedWhenBatteryLevelIsLowerThanExpected() {
        assertTrue(mRule.isSatisfied());
    }

    public void testIsSatisfiedWhenBatteryLevelIsHigherThanExpected() {
        mRule = new LowBatteryRule("15");
        Intent intent = new Intent();
        intent.putExtra(BatteryManager.EXTRA_LEVEL, 2500);
        intent.putExtra(BatteryManager.EXTRA_SCALE, 100);
        mRule.setIntent(intent);
        assertFalse(mRule.isSatisfied());
    }

    public void testGetRuleDescription() {
        assertEquals("Battery level lower than 15%", mRule.getRuleDescription());
    }

    public void testConvertToString() {
        assertEquals(LowBatteryRule.TAG + Rule.ARGS_SEPARATOR + 15, mRule.convertToString());
    }

}
