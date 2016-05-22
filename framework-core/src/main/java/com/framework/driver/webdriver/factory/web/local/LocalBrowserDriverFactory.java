package com.framework.driver.webdriver.factory.web.local;

import com.framework.core.utils.TestContext;
import com.framework.driver.WebDriverProperties;
import org.openqa.selenium.WebDriver;
//import org.junit.Assert;

/**
 * Created by Marinko on 2016-05-21.
 */
public abstract class LocalBrowserDriverFactory {

    protected static int browserRetryCount = 5;
    protected static int browserAttempt = 1;

    public abstract WebDriver createDriver(WebDriverProperties driverProperties) throws Exception;

    /*
    protected static void cannotEmulateMobile(String browser) throws Exception {
        Assert.assertTrue("Mobile emulation not supported on " + browser, !TestContext.isMobileChannel());
    }
    */
}
