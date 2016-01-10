package com.icetea09.droidmax.utils;

import android.test.AndroidTestCase;

/**
 * Created by KhanhTrinh on 1/10/2016.
 */
public class StringUtilsTest extends AndroidTestCase {

    public void testConvertStringArrayToStringWithDelimiterWithEmptyString() {
        assertEquals("", StringUtils.convertStringArrayToStringWithDelimiter(new String[0], "#"));
    }

    public void testConvertStringArrayToStringWithDelimiter() {
        String[] input = new String[]{"One", "Two", "Three"};
        assertEquals("One#Two#Three", StringUtils.convertStringArrayToStringWithDelimiter(input, "#"));
    }

}
