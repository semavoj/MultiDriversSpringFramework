package com.framework.driver.webdriver.factory.web.local.provider;

import com.framework.driver.webdriver.browser.BrowserSettings;
import com.framework.driver.webdriver.factory.web.local.LocalBrowserDriverFactory;
import com.framework.driver.webdriver.factory.web.local.impl.ChromeDriverFactory;

/**
 * Created by Marinko on 2016-05-21.
 */
public class LocalBrowserDriverFactoryProvider {

    public static LocalBrowserDriverFactory getDriverFactory(BrowserSettings.WebBrowser browser) {

        LocalBrowserDriverFactory factory = null;

        switch (browser) {
            case FIREFOX:
                //factory = new FirefoxDriverFactory();
                break;
            case SAFARI:
                break;
            case OPERA:
                break;
            case CHROME:
                factory = new ChromeDriverFactory();
                break;
            case HTMLUNIT:
                //factory = new HtmlUnitDriverFactory();
                break;
            case IE:
                //factory = new IEDriverFactory();
                break;
            default:
                throw new IllegalArgumentException("Browser type " + browser + " is not supported!");
        }

        return factory;
    }
}
