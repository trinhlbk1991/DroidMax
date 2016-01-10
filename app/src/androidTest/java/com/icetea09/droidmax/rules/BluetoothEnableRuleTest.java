package com.icetea09.droidmax.rules;

import android.bluetooth.BluetoothAdapter;
import android.test.AndroidTestCase;

import com.icetea09.droidmax.rules.bluetooth.BluetoothEnableRule;

/**
 * Created by KhanhTrinh on 1/10/2016.
 */
public class BluetoothEnableRuleTest extends AndroidTestCase {

    BluetoothEnableRule mRule;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mRule = new BluetoothEnableRule();
    }

    public void testIsSatisfiedWithNoBluetooth() {
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        mBluetoothAdapter.disable();
        assertFalse(mRule.isSatisfied());
    }

    public void testGetRuleDescription() {
        assertEquals("Bluetooth turns on", mRule.getRuleDescription());
    }

    public void testConvertToString() {
        assertEquals(BluetoothEnableRule.TAG, mRule.convertToString());
    }

}
