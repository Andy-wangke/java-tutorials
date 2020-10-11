package com.it.common.utils;

public class StringUtils {

    public static boolean isDigitOnly(String chkStr) {
        if (chkStr == null)
            return false;
        for (char c : chkStr.toCharArray())
            if (!Character.isDigit(c))
                return false;
        return true;
    }

    public static String join(byte[] items, String delimiter) {
        StringBuffer sb = new StringBuffer();
        for (byte b : items) {
            if (sb.length() > 0)
                sb.append(delimiter);
            sb.append(b & 0xff);
        }
        return sb.toString();
    }
}
