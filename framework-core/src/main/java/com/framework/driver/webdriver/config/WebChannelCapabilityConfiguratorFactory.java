package com.framework.driver.webdriver.config;

import com.framework.driver.webdriver.config.builder.web.ChromeWebDriverCapabilityBuilder;

/**
 * Created by Marinko on 2016-05-21.
 */
public class WebChannelCapabilityConfiguratorFactory {

    private static final String CHROME = "chrome";
    private static final String FIREFOX = "firefox";
    private static final String IE = "ie";
    private static final String EXPLORER = "explorer";
    private static final String SAFARI = "safari";
    private static final String EDGE = "edge";

    public static CapabilityConfigurator getConfigurator(String browser) {

        CapabilityConfigurator config = null;

        if (browser.equalsIgnoreCase(CHROME)) {
            config = new CapabilityConfigurator(new ChromeWebDriverCapabilityBuilder());
        } /*else if (browser.equalsIgnoreCase(FIREFOX)) {
            config = new CapabilityConfigurator(new FirefoxWebDriverCapabilityBuilder());
        } else if (browser.equalsIgnoreCase(IE) || browser.contains(EXPLORER)) {
            config = new CapabilityConfigurator(new IECapabilityBuilder());
        } else if ( browser.contains(EDGE)) {
            config = new CapabilityConfigurator(new EdgeWebDriverCapabilityBuilder());
        } else if (browser.equalsIgnoreCase(SAFARI)){
            config = new CapabilityConfigurator(new SafariWebDriverCapabilityBuilder());
        } */else {
            throw new IllegalArgumentException("Browser " + browser + " not supported!");
        }

        return config;
    }

}
