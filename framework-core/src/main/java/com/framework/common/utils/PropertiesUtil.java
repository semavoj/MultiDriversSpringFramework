package com.framework.common.utils;

import com.framework.common.context.SpringApplicationContext;
import com.framework.core.utils.TestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Created by Marinko on 2016-05-21.
 */
public class PropertiesUtil {

    private final static Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);

    public static Properties loadPropertiesFile(String fileName) throws Exception{
        logger.info("Loading property file : " + fileName);
        Properties props = new Properties();
        props.load(PropertiesUtil.class.getResourceAsStream(fileName));

        return props;
    }

    public ReloadableResourceBundleMessageSource loadTranslationsFiles() {
        logger.info("Loading resource bundle : /translations/" + /*TestContext.getBrand()*/  "/" + TestContext.getChannel() + "/translations");
        ReloadableResourceBundleMessageSource messages = (ReloadableResourceBundleMessageSource) SpringApplicationContext.getBean("messageSource");
        return messages;
    }

    public ResourceBundle loadPropertiesFile(String fileName, String language, String country) {
        logger.info("Loading property file : " + fileName + " (Language: " + language + " Country: " + country + ")");
        Locale currentLocale = new Locale(language, country);
        ResourceBundle messages = ResourceBundle.getBundle(fileName, currentLocale);
        return messages;
    }
}