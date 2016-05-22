package com.framework.driver.webdriver.config;

import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Marinko on 2016-05-21.
 */
public class CapabilityConfigurator {

    private DesiredCapabilities desiredCapabilities;
    private CapabilityBuilder capabilityBuilder;

    private final static Logger logger = LoggerFactory.getLogger(CapabilityConfigurator.class);

    public CapabilityConfigurator(CapabilityBuilder capabilityBuilder) {
        this.capabilityBuilder = capabilityBuilder;
    }

    public DesiredCapabilities getDesiredCapabilities() {
        return desiredCapabilities;
    }

    public void setDesiredCapabilities(DesiredCapabilities desiredCapabilities) {
        this.desiredCapabilities = desiredCapabilities;
    }

    public CapabilityBuilder getCapabilityBuilder() {
        return capabilityBuilder;
    }

    public void setCapabilityBuilder(CapabilityBuilder capabilityBuilder) {
        this.capabilityBuilder = capabilityBuilder;
    }

    public CapabilityConfigurator build() {
        this.capabilityBuilder.buildDesiredCapabilities();
        this.desiredCapabilities = capabilityBuilder.getDesiredCapabilities();
        return this;
    }

    public CapabilityConfigurator buildForPlatform(String platform) {
        this.capabilityBuilder.buildDesiredCapabilities();
        this.capabilityBuilder.setPlatform(platform);
        this.desiredCapabilities = capabilityBuilder.getDesiredCapabilities();
        return this;
    }

    @Override
    public String toString() {
        StringBuilder printout = new StringBuilder();
        return printout.append("Desired Capabilities: {").append(desiredCapabilities.toString()).append("}").toString();
    }

    public abstract static class CapabilityBuilder {

        protected DesiredCapabilities desiredCapabilities;

        public DesiredCapabilities getDesiredCapabilities() {
            return desiredCapabilities;
        }

        public void setPlatform(String platform) {
            if (platform != null) {
                platform = platform.toLowerCase();
                if (platform.contains("win")) {
                    this.desiredCapabilities.setPlatform(Platform.WINDOWS);
                } else if (platform.equalsIgnoreCase("linux")) {
                    this.desiredCapabilities.setPlatform(Platform.LINUX);
                } else if (platform.equalsIgnoreCase("mac")) {
                    this.desiredCapabilities.setPlatform(Platform.MAC);
                } else if (platform.equalsIgnoreCase("android")) {
                    this.desiredCapabilities.setPlatform(Platform.ANDROID);
                }
            }
        }

        public abstract void buildDesiredCapabilities();
    }

    public abstract static class CapabilityBuilderDecorator extends CapabilityBuilder {

        protected CapabilityBuilder builder;

        public CapabilityBuilderDecorator(CapabilityBuilder builder) {
            this.builder = builder;
        }

        public abstract void buildDesiredCapabilities();
    }
}
