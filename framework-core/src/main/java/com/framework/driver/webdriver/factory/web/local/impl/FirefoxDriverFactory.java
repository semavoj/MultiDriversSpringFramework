package com.framework.driver.webdriver.factory.web.local.impl;

import com.framework.core.utils.TestContext;
import com.framework.driver.Profile;
import com.framework.driver.WebDriverProperties;
import com.framework.driver.webdriver.factory.web.local.LocalBrowserDriverFactory;
import com.framework.driver.webdriver.utils.PathUtils;
import com.framework.driver.webdriver.utils.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * Created by Marinko on 2016-05-22.
 */
public class FirefoxDriverFactory extends LocalBrowserDriverFactory{
    private final static Logger logger = LoggerFactory.getLogger(FirefoxDriverFactory.class);

    @Override
    public WebDriver createDriver(WebDriverProperties driverProperties) throws Exception {
        WebDriver driver = null;
        try {
            cannotEmulateMobile(TestContext.getBrowser());
            Profile.SimpleProfile profile = new Profile.SimpleProfile(driverProperties.getProfilePath());
            String port = System.getProperty("webdriver.firefox.port");
            if (port != null)
                logger.info("Trying to open Firefox browser on port " + System.getProperty("webdriver.firefox.port"));
            driver = new FirefoxDriver(deriveFirefoxBinary(driverProperties),
                    profile.firefoxProfile(Boolean.valueOf(driverProperties.getEnableNativeEvents()).booleanValue()),
                    profile.DesiredCapabilities());
            browserAttempt = 1; //reset the counter
        } catch (Exception e) {
            logger.info("Error while initializing the browser object:" + e.getMessage());
            String bindErrorMessage = "Unable to bind to locking port";
            if ((e.getMessage().contains(bindErrorMessage)) && (browserAttempt <= browserRetryCount)) {
                logger.info("Attempt " + (browserAttempt + 1) + " browser instance creation!");
                browserAttempt++;
                int newPort = 7060 + new java.util.Random().nextInt(10);
                System.setProperty("webdriver.firefox.port", newPort + "");
                createDriver(driverProperties);
            } else {
                throw e;
            }
        }
        return driver;
    }

    private static FirefoxBinary deriveFirefoxBinary(WebDriverProperties driverProperties) {
        String scriptPath = driverProperties.getScriptPath();
        FirefoxBinary firefoxBinary;
        if (!StringUtils.isEmpty(scriptPath)) {
            firefoxBinary = new FirefoxBinary(new File(PathUtils.escapeSpecialCharsInPath(scriptPath)));
        } else {
            firefoxBinary = new FirefoxBinary();
        }
        return firefoxBinary;
    }

}
