package com.it.common.utils;


public class EnumUtil {

    /**
     * Similar to Enum.valueOf(), but instead of throwing exception, this one will return defaultValue
     * if the value is invalid.
     * @param cls  The enum class
     * @param name  The name of the enum value
     * @defaultValue  The default value if there's no matching enum.
     * @return
     */
    public static <T extends Enum<T>> T valueOf(Class<T> cls, String name, T defaultValue) {
        try {
            return Enum.valueOf(cls, name);
        } catch (NullPointerException | IllegalArgumentException e) {
            return defaultValue;
        }
    }
    
    /**
     * Similar to Enum.valueOf(), but instead of throwing exception, this one will return defaultValue
     * if the value is invalid.
     * @param cls  The enum class
     * @param name  The name of the enum value
     * @defaultValue  The default value if there's no matching enum.
     * @return
     */
    public static <T extends Enum<T>> T valueOf(Class<T> cls,String name){
        return valueOf(cls, name, null);
    }
}
