package com.framework.driver.webdriver.factory.web.local.impl;

import com.framework.core.utils.TestContext;
import com.framework.driver.WebDriverProperties;
import com.framework.driver.webdriver.SeleniumServerConfig;
import com.framework.driver.webdriver.config.CapabilityConfigurator;
import com.framework.driver.webdriver.config.WebChannelCapabilityConfiguratorFactory;
import com.framework.driver.webdriver.factory.web.local.LocalBrowserDriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Marinko on 2016-05-21.
 */
public class ChromeDriverFactory extends LocalBrowserDriverFactory {private final static Logger logger = LoggerFactory.getLogger(ChromeDriverFactory.class);

    @Override
    public WebDriver createDriver(WebDriverProperties driverProperties) throws Exception {
        CapabilityConfigurator config = WebChannelCapabilityConfiguratorFactory.getConfigurator(TestContext.getBrowser()).buildForPlatform(TestContext.getPlatform());
        ChromeOptions options = (ChromeOptions) config.getDesiredCapabilities().getCapability(ChromeOptions.CAPABILITY);
        return new ChromeDriver((ChromeDriverService) new SeleniumServerConfig(driverProperties.getChromeServerLocation()).getService(), options);
    }
}
