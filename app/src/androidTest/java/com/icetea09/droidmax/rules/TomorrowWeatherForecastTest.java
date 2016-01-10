package com.icetea09.droidmax.rules;

import android.test.AndroidTestCase;

import com.icetea09.droidmax.model.Rule;
import com.icetea09.droidmax.rules.weatherforecast.TomorrowWeatherForecastRule;

/**
 * Created by KhanhTrinh on 1/10/2016.
 */
public class TomorrowWeatherForecastTest extends AndroidTestCase {

    TomorrowWeatherForecastRule mRule;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mRule = new TomorrowWeatherForecastRule("Clear", "20:00");
    }

    public void testIsSatisfiedWithEmptyLocation() {
        mRule.setCurrentLocation(0, 0);
        assertFalse(mRule.isSatisfied());
    }

    public void testIsSatisfiedWithProperNetwork() {
        // mRule.setCurrentLocation(106.666672, 10.75);
        //assertFalse(mRule.isSatisfied());
    }

    public void testGetRuleDescription() {
        assertEquals("Tomorrow's weather is Clear", mRule.getRuleDescription());
    }

    public void testConvertToString() {
        assertEquals(TomorrowWeatherForecastRule.TAG + Rule.ARGS_SEPARATOR + "Clear" + Rule.ARGS_SEPARATOR
                + "20:00", mRule.convertToString());
    }

}
