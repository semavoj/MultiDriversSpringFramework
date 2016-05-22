package com.framework.driver.webdriver.config.builder.web;

import com.framework.driver.webdriver.config.CapabilityConfigurator;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Marinko on 2016-05-22.
 */
public class FirefoxWebDriverCapabilityBuilder extends CapabilityConfigurator.CapabilityBuilder{

    private final static Logger logger = LoggerFactory.getLogger(FirefoxWebDriverCapabilityBuilder.class);

    @Override
    public void buildDesiredCapabilities() {
        this.desiredCapabilities = DesiredCapabilities.firefox();
        FirefoxProfile firefoxProfile = new FirefoxProfile();
        //Disable Firefox reader view
        firefoxProfile.setPreference("reader.parse-on-load.enabled", false);
        firefoxProfile.setEnableNativeEvents(false);
        this.desiredCapabilities.setCapability(FirefoxDriver.PROFILE, firefoxProfile);
    }

}
