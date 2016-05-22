package com.framework.driver;

import java.net.URL;

/**
 * Created by Marinko on 2016-05-21.
 */
public interface WebDriverProperties
{
    String getHtmlUnitBrowserVersion();

    boolean isJavascriptEnabled();

    String getChromeServerLocation();

    String getEnableNativeEvents();

    String getScriptPath();

    String getProfilePath();

    String getIEServerLocation();

    URL getRemoteServerAddress();
}