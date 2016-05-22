package com.framework.driver.webdriver.utils;

/**
 * Created by Marinko on 2016-05-21.
 */
public class StringUtils
{
    public static boolean isEmpty(String text)
    {
        return (text == null) || (text.trim().equals(""));
    }

    public static String join(Object[] array, String delimiter)
    {
        StringBuilder delimeterSeparatedString = new StringBuilder();
        Object[] arrayOfObject = array;int j = array.length;
        for (int i = 0; i < j; i++)
        {
            Object entry = arrayOfObject[i];
            delimeterSeparatedString.append(entry.toString()).append(delimiter);
        }
        if (isEmpty(delimeterSeparatedString.toString())) {
            return "";
        }
        return delimeterSeparatedString.substring(0, delimeterSeparatedString.length() - delimiter.length());
    }

    public static String tab()
    {
        return "\t";
    }

    public static String nl()
    {
        return System.getProperty("line.separator");
    }

    public static String fileSeparator()
    {
        return System.getProperty("file.separator");
    }
}
