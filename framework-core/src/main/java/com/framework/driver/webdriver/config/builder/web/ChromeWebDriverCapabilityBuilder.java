package com.framework.driver.webdriver.config.builder.web;

import com.framework.driver.webdriver.config.CapabilityConfigurator;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Marinko on 2016-05-21.
 */
public class ChromeWebDriverCapabilityBuilder extends CapabilityConfigurator.CapabilityBuilder{

    private final static Logger logger = LoggerFactory.getLogger(ChromeWebDriverCapabilityBuilder.class);

    @Override
    public void buildDesiredCapabilities() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-extensions");
        chromeOptions.addArguments("test-type");
//      chromeOptions.addArguments("--window-size=300,300");
        this.desiredCapabilities = DesiredCapabilities.chrome();
        this.desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
    }
}
