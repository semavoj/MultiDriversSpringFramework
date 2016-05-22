package com.framework.driver.webdriver.utils;

/**
 * Created by Marinko on 2016-05-22.
 */
public class PathUtils {
    public static String escapeSpecialCharsInPath(String toEscape)
    {
        return toEscape.replaceAll("\\\\", "\\\\\\\\").replaceAll(" ", "\\ ");
    }

    public static String resolveClassName(String text)
    {
        return text.split("\\.")[0];
    }
}
