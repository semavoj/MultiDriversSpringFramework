package com.framework.driver.webdriver.utils;

import java.io.File;

/**
 * Created by Marinko on 2016-05-21.
 */
public enum  OSArch {
        WIN,  LINUX_32,  LINUX_64,  MACOSX,  NONE;

        protected static String bit;
        protected static boolean isWindows, isMac, isLinux = false;

    public static OSArch currentOSArch()
    {
        return forOSArch(System.getProperty("os.name"),
                System.getProperty("os.arch"));
    }

    public static OSArch forOSArch(String osName, String osArch)
    {
        is32Bit(osArch);

        if (isWindows(osName)) {
            isWindows = true;
            return WIN;
        }
        if (isLinux(osName)) {
            isLinux = true;
            if(bit.equals("32")) {
                return LINUX_32;
            }
            else {
                return LINUX_64;
            }
        }
        if (isMac(osName)) {
            isMac = true;
            return MACOSX;
        }
        return NONE;
    }

    private static boolean is32Bit(String osArch) {
        bit = osArch.endsWith("86") ? "32" : "64";
        return bit.equals("32");
    }

    private static boolean isMac(String osName) {
        return stringHasSubstring(osName, "Mac");
    }

    private static boolean isWindows(String osName) {
        return stringHasSubstring(osName, "windows");
    }

    private static boolean isLinux(String osName) {
        return (stringHasSubstring(osName, "Linux")) ||
                (stringHasSubstring(osName, "FreeBSD")) ||
                (stringHasSubstring(osName, "SunOS"));
    }

    private static boolean stringHasSubstring(String osName, String string) {
        return osName.toLowerCase().contains(string.toLowerCase());
    }

    public String chromeBinary() {
        if(isWindows) {
            return ".exe";
        }
        else if(isMac) {
            return "_mac";
        }
        else {
            return "_linux_" + bit;
        }
    }

    public String internetExplorerBinary() {
        // If IE set to 32bit  executable
        bit = "32";
        return bit + ".exe";
    }

    public void makeBinaryExecutable(File chromeBinary) {
        if ((chromeBinary.exists()) && (!chromeBinary.canExecute())) {
            try {
                chromeBinary.setExecutable(true);
            }
            catch (SecurityException e) {
                throw new RuntimeException("Error setting execute permission to file " + chromeBinary.getAbsolutePath(), e);
            }
        }
    }
}
