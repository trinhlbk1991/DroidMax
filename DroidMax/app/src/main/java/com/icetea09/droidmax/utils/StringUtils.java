package com.icetea09.droidmax.utils;

/**
 * Created by KhanhTrinh on 1/9/2016.
 */
public class StringUtils {

    public static String convertStringArrayToStringWithDelimiter(String[] strings, String delimiter) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String str : strings) {
            stringBuilder.append(str).append(delimiter);
        }
        if (strings.length != 0) stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }

}
