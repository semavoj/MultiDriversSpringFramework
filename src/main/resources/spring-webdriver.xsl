<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-3.0.xsd">


    <!-- ============================================================ -->
    <!-- Create and initialize the factory that will create the bean. -->
    <!-- ============================================================ -->
    <bean class="com.unibet.unitard.driver.webdriver.WebDriverFactory"
          destroy-method="stop" id="webDriverFactory" init-method="start"
          lazy-init="true">
        <property name="enableNativeEvents" value="${webdriver.browser.nativeEvents}" />
        <property name="javascriptEnabled" value="${webdriver.browser.javascriptEnabled}" />
        <property name="htmlUnitBrowserVersion" value="${webdriver.browser.version}" />
        <property name="scriptPath" value="${webdriver.browser.script}" />
        <property name="profilePath" value="${webdriver.browser.profile}" />
        <property name="mobileServerAddress" value="${webdriver.mobile.server}" />
        <property name="chromeServer" value="${webdriver.chrome.driver}" />
        <property name="ieServer" value="${webdriver.ie.driver}" />
        <property name="remoteServerAddress" value="${webdriver.${vendor}.grid.server}" />
    </bean>

    <bean destroy-method="quit" factory-bean="webDriverFactory" factory-method="getBrowser" id="browser" lazy-init="true" scope="${webdriver-scope:cucumber-glue}">
        <constructor-arg value="${webdriver.browser}" />
    </bean>

    <bean destroy-method="close" factory-bean="webDriverFactory" factory-method="getNgDriver" id="ngDriver" lazy-init="true" scope="${webdriver-scope:cucumber-glue}">
        <constructor-arg ref="browser"/>
    </bean>

    <bean class="com.paulhammant.ngwebdriver.ByAngular" id="ng" lazy-init="true" scope="cucumber-glue">
        <constructor-arg ref="browser"/>
    </bean>

    <bean class="com.unibet.unitard.driver.InitDriverFactory" />

</beans>