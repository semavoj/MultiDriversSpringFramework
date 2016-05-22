package com.framework.common.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Created by Marinko on 2016-05-21.
 */
@Component
public class SpringApplicationContext implements ApplicationContextAware {

    private static ApplicationContext CONTEXT;

    public static Object getBean(String beanName) {
        return CONTEXT.getBean(beanName);
    }

    public void setApplicationContext(ApplicationContext context) throws BeansException {
        CONTEXT = context;
    }
}