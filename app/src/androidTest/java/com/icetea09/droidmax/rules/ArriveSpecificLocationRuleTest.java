package com.icetea09.droidmax.rules;

import android.location.Location;
import android.test.AndroidTestCase;

import com.icetea09.droidmax.model.Rule;
import com.icetea09.droidmax.rules.location.ArriveSpecificLocation;

/**
 * Created by KhanhTrinh on 1/10/2016.
 */
public class ArriveSpecificLocationRuleTest extends AndroidTestCase {

    ArriveSpecificLocation mRule;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mRule = new ArriveSpecificLocation("Home", "100", "200");
    }

    public void testIsSatisfiedWithNoCurrentLocation() {
        mRule.setCurrentLocation(null);
        assertFalse(mRule.isSatisfied());
    }

    public void testIsSatisfiedWithFarFromHomeLocation() {
        Location currentLocation = new Location("network");
        currentLocation.setLongitude(20);
        currentLocation.setLatitude(50);
        mRule.setCurrentLocation(currentLocation);
        assertFalse(mRule.isSatisfied());
    }

    public void testIsSatisfiedWithNearHomeLocation() {
        Location currentLocation = new Location("network");
        currentLocation.setLongitude(100);
        currentLocation.setLatitude(200);
        mRule.setCurrentLocation(currentLocation);
        assertTrue(mRule.isSatisfied());
    }

    public void testGetRuleDescription() {
        assertEquals("Arrive Home", mRule.getRuleDescription());
    }

    public void testConvertToString() {
        assertEquals(ArriveSpecificLocation.TAG + Rule.ARGS_SEPARATOR + "Home" + Rule.ARGS_SEPARATOR
                + 100.0 + Rule.ARGS_SEPARATOR + 200.0, mRule.convertToString());
    }

}
