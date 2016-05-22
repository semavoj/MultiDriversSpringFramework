package com.framework.driver.webdriver;

import com.framework.core.utils.TestContext;
import com.framework.driver.WebDriverProperties;
import com.framework.driver.webdriver.factory.web.StandaloneDriverFactory;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
//import com.jprotractor.NgWebDriver;

/**
 * Created by Marinko on 2016-05-21.
 */
public class WebDriverFactory implements WebDriverProperties {

    private String chromeServerLocation = "";
    private String IEServerLocation = "";
    private String enableNativeEvents = "false";
    private String htmlUnitBrowserVersion = "";
    private boolean javascriptEnabled = false;
    private String profilePath = "";
    private URL remoteServerAddress = null;
    private String scriptPath = "";
    private String mobileServerAddress;

    public String getChromeServerLocation()
    {
        return this.chromeServerLocation;
    }

    public void setChromeServer(String chromeServer)
    {
        this.chromeServerLocation = chromeServer;
    }

    public String getIEServerLocation()
    {
        return this.IEServerLocation;
    }

    public void setIeServer(String ieServer)
    {
        this.IEServerLocation = ieServer;
    }

    public String getEnableNativeEvents()
    {
        return this.enableNativeEvents;
    }

    public void setEnableNativeEvents(String enableNativeEvents)
    {
        this.enableNativeEvents = enableNativeEvents;
    }

    public String getHtmlUnitBrowserVersion()
    {
        return this.htmlUnitBrowserVersion;
    }

    public void setHtmlUnitBrowserVersion(String htmlUnitBrowserVersion)
    {
        if(TestContext.getBrowser().equals("htmlunit")) {
            this.htmlUnitBrowserVersion = htmlUnitBrowserVersion;
        }
    }

    public URL getRemoteServerAddress() {
        return  this.remoteServerAddress;
    }

    public boolean isJavascriptEnabled()
    {
        return this.javascriptEnabled;
    }

    public void setJavascriptEnabled(boolean javascriptEnabled)
    {
        this.javascriptEnabled = javascriptEnabled;
    }

    public String getProfilePath() { return this.profilePath; }

    public void setProfilePath(String profilePath)
    {
        this.profilePath = profilePath;
    }

    public String getScriptPath()
    {
        return this.scriptPath;
    }

    public void setScriptPath(String scriptLocation)
    {
        this.scriptPath = scriptLocation;
    }

    public synchronized WebDriver getBrowser(String browserName) throws Exception {
        WebDriver browser = StandaloneDriverFactory.createWebdriverBrowser(browserName, this);
        browser.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
        return browser;
    }
/*
    public synchronized NgWebDriver getNgDriver(WebDriver browser) throws Exception {
        return new NgWebDriver(browser, true);
    }
*/
    public void start() {}

    public void stop() {}

    public void setMobileServerAddress(String mobileServerAddress)
    {
        this.mobileServerAddress = mobileServerAddress;
    }

    public void setRemoteServerAddress(URL remoteServerAddress)
    {
        this.remoteServerAddress = remoteServerAddress;
    }

    public String getMobileServerAddress()
    {
        return this.mobileServerAddress;
    }
}
