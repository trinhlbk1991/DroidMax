package com.icetea09.droidmax.rules;

import android.bluetooth.BluetoothAdapter;
import android.test.AndroidTestCase;

import com.icetea09.droidmax.rules.bluetooth.BluetoothDisableRule;

/**
 * Created by KhanhTrinh on 1/10/2016.
 */
public class BluetoothDisableRuleTest extends AndroidTestCase {

    BluetoothDisableRule mRule;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mRule = new BluetoothDisableRule();
    }

    public void testIsSatisfiedWithNoBluetooth() {
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        mBluetoothAdapter.disable();
        assertTrue(mRule.isSatisfied());
    }

    public void testGetRuleDescription() {
        assertEquals("Bluetooth turns off", mRule.getRuleDescription());
    }

    public void testConvertToString() {
        assertEquals(BluetoothDisableRule.TAG, mRule.convertToString());
    }

}
