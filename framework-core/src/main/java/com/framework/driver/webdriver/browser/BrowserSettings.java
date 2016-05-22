package com.framework.driver.webdriver.browser;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Marinko on 2016-05-21.
 */
public interface BrowserSettings {

    String[] getSupportedBrowsers();

    String getDefaultBrowserPath(String paramString);

    enum WebBrowser
    {
        SAFARI,  OPERA,  CHROME,  FIREFOX,  IE,  HTMLUNIT,  OTHER;

        public static WebBrowser byText(String value)
        {
            WebBrowser[] browsers = values();
            for (WebBrowser browser : browsers) {
                if (browser != null)
                    if (browser.getDisplayString().equalsIgnoreCase(value)) {
                        return browser;
                    }
            }
            return null;
        }

        public static WebBrowser byValue(String value)
        {
            if (value != null)
            {
                String browserType = value.trim().replaceAll("\\ ", "_").toUpperCase();
                for (WebBrowser browser : values()) {
                    if (browser.name().equals(browserType)) {
                        return browser;
                    }
                }
            }
            return null;
        }

        public static List<WebBrowser> getAllBrowsers()
        {
            return Arrays.asList(SAFARI, FIREFOX, IE, CHROME);
        }

        public static List<WebBrowser> getBrowsersNeedingProxy()
        {
            return Arrays.asList(CHROME, IE, OPERA);
        }

        public String getDisplayString() {
            return WebBrowser.this.toString();
        }

        public String driverClassName() {
            return null;
        }
    }
}
