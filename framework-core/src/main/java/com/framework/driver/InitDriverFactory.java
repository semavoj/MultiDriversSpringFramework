package com.framework.driver;

import java.net.MalformedURLException;
import java.net.URL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.BeansException;

/**
 * Created by Marinko on 2016-05-20.
 */
//Used for bean initialization
public class InitDriverFactory implements BeanPostProcessor{

    private final static Logger logger = LoggerFactory.getLogger(InitDriverFactory.class);

    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        /*
        if(TestContext.getVendor().equals(TestContext.VendorType.SAUCELABS)
                || TestContext.getVendor().equals(TestContext.VendorType.BROWSERSTACK)) {
            if (beanName.equalsIgnoreCase("WebDriverFactory")) {
                postProcessWebRemoteServerAddress(bean);
            } else if (beanName.equalsIgnoreCase("AppiumDriverFactory")) {
                postProcessAppiumRemoteServerAddress(bean);
            }
        }*/
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        /*
        if(TestContext.isRunOnGrid() && beanName.equalsIgnoreCase("webDriverFactory"))
            logger.info("PostProcessAfter: WebDriverFactory remoteServerAddress:" + ((WebDriverFactory) bean).getRemoteServerAddress());
        */
        return bean;
    }

    /*
    private void postProcessWebRemoteServerAddress(Object bean){

        URL remoteServerAddress = ((WebDriverFactory) bean).getRemoteServerAddress();

        String strRemoteServerAddress = remoteServerAddress.toString()
                .replace("<CLOUD_USERNAME>", TestContext.getCloudUsername())
                .replace("<CLOUD_ACCESS_KEY>", TestContext.getCloudAccessKey());

        try {

            remoteServerAddress = new URL(strRemoteServerAddress);

        } catch (MalformedURLException e) {
            logger.error("PostProcessBefore: Error while setting the remote server address:" + e.getMessage());
        }

        ((WebDriverFactory) bean).setRemoteServerAddress(remoteServerAddress);
    }

    private void postProcessAppiumRemoteServerAddress(Object bean){

        String remoteServerAddress = ((AppiumDriverFactory) bean).getServerAddress()
                .replace("<SAUCE_USERNAME>", TestContext.getCloudUsername())
                .replace("<SAUCE_ACCESS_KEY>", TestContext.getCloudAccessKey());

        ((AppiumDriverFactory) bean).setServerAddress(remoteServerAddress);

    }*/
}
