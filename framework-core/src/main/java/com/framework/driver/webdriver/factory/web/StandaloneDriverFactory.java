package com.framework.driver.webdriver.factory.web;

import com.framework.core.utils.TestContext;
import com.framework.driver.WebDriverProperties;
import com.framework.driver.webdriver.browser.BrowserSettings;
import com.framework.driver.webdriver.factory.web.local.provider.LocalBrowserDriverFactoryProvider;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Created by Marinko on 2016-05-21.
 */
public class StandaloneDriverFactory {

    private final static Logger logger = LoggerFactory.getLogger(StandaloneDriverFactory.class);

    public static WebDriver createWebdriverBrowser(String browserName, WebDriverProperties driverProperties) throws Exception {

        WebDriver webDriverBrowser = null;

        if (TestContext.isRunOnGrid()) {
           // webDriverBrowser = createGridDriver(driverProperties);
        } else {
            webDriverBrowser = createLocalDriver(browserName, driverProperties);
        }

        WebDriver.Timeouts timeouts = webDriverBrowser.manage().timeouts();
        timeouts.implicitlyWait(10L, TimeUnit.SECONDS).setScriptTimeout(10L, TimeUnit.SECONDS);

        return webDriverBrowser;
    }

    /*
    private static WebDriver createGridDriver(WebDriverProperties driverProperties) throws Exception {
        URL gridUrl = driverProperties.getRemoteServerAddress();

        WebDriver driver = null;

        if (TestContext.isWebChannel()) {
            driver = RemoteWebChannelDriverFactoryProvider.getDriverFactory().createDriver(gridUrl);
        } else {
            driver = RemoteMobileChannelDriverFactoryProvider.getDriverFactory().createDriver(gridUrl);
        }

        return driver;
    }
    */

    private static WebDriver createLocalDriver(String browserName, WebDriverProperties driverProperties) throws Exception {

        BrowserSettings.WebBrowser browser = BrowserSettings.WebBrowser.byValue(browserName);
        if (browser == null) {
            throw new RuntimeException("Could not identify specified browser: " + browserName);
        }

        logger.info("Attempting to create " + browser + " browser");

        WebDriver driver = null;

        if (TestContext.isWebChannel()) {
            driver = LocalBrowserDriverFactoryProvider.getDriverFactory(browser).createDriver(driverProperties);
        } else {
            //driver = LocalMobileChannelDriverFactoryProvider.getDriverFactory().createDriver(driverProperties);
        }

        return driver;
    }
}
