package com.framework.core.utils;

import com.framework.common.utils.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Marinko on 2016-05-21.
 */
@Component
@Lazy
@Scope("cucumber-glue")
public class TestContext {

    private final static Logger logger = LoggerFactory.getLogger(TestContext.class);

    private static final String VM_ARGS_BROWSER = "browser";
    private static final String VM_ARGS_TEST_ENV = "test.env";
    private static final String VM_ARGS_BRAND = "brand";
    private static final String VM_ARGS_PLATFORM = "platform";
    private static final String VM_ARGS_CHANNEL = "channel";
    private static final String VM_ARGS_JURISDICTION = "jurisdiction";
    private static final String VM_ARGS_MARKET = "market";
    private static final String VM_ARGS_DEVICE = "device";
    private static final String VM_ARGS_VENDOR = "vendor";
    private static final String VM_ARGS_RUN_ON_GRID = "runOnGrid";
    private static final String VM_ARGS_APPIUM_VERSION = "appiumVersion";
    private static final String VM_ARGS_DEVICE_ORIENTATION = "deviceOrientation";
    private static final String VM_ARGS_BROWSER_VERSION = "browserVersion";
    private static final String VM_ARGS_RECORD_VIDEO = "recordVideo";
    private static final String VM_ARGS_APP = "app";
    private static final String VM_ARGS_DEVICE_ID = "deviceId";
    private static final String VM_ARGS_BUNDLE_ID = "bundleId";
    private static final String VM_ARGS_TEST_NAME = "name";
    private static final String VM_ARGS_APP_PACKAGE = "appPackage";
    private static final String VM_ARGS_APP_ACTIVITY = "appActivity";

    private static final String DEFAULT_APPIUM_VERSION = "1.4.13";
    public static final String ENV_SAUCE_USERNAME = "SAUCE_USERNAME";
    public static final String ENV_SAUCE_ACCESS_KEY = "SAUCE_ACCESS_KEY";

    public static final String ENV_BROWSER_STACK_USERNAME = "BS_USERNAME";
    public static final String ENV_BROWSER_STACK_ACCESS_KEY = "BS_ACCESS_KEY";

    // test setup related arguments
    private static String browser = System.getProperty(VM_ARGS_BROWSER);
    private static String env = System.getProperty(VM_ARGS_TEST_ENV);
    private static String brand = System.getProperty(VM_ARGS_BRAND);
    private static String platform = System.getProperty(VM_ARGS_PLATFORM);
    private static String channel = System.getProperty(VM_ARGS_CHANNEL);
    private static String jurisdiction = System.getProperty(VM_ARGS_JURISDICTION);
    private static String market = System.getProperty(VM_ARGS_MARKET);

    private static String device = System.getProperty(VM_ARGS_DEVICE);
    private static String vendor = System.getProperty(VM_ARGS_VENDOR, VendorType.AWS.toString());
    private static boolean runOnGrid = Boolean.parseBoolean(System.getProperty(VM_ARGS_RUN_ON_GRID));
    private static String appiumVersion = System.getProperty(VM_ARGS_APPIUM_VERSION, DEFAULT_APPIUM_VERSION);
    private static String deviceOrientation = System.getProperty(VM_ARGS_DEVICE_ORIENTATION, DeviceOrientation.PORTRAIT.toString());
    private static String browserVersion = System.getProperty(VM_ARGS_BROWSER_VERSION, "");
    private static boolean recordVideo = Boolean.parseBoolean(System.getProperty(VM_ARGS_RECORD_VIDEO, "false"));
    private static String app = System.getProperty(VM_ARGS_APP);
    private static String deviceId = System.getProperty(VM_ARGS_DEVICE_ID);
    private static String bundleId = System.getProperty(VM_ARGS_BUNDLE_ID);
    private static String testName = System.getProperty(VM_ARGS_TEST_NAME);
    private static String appPackage = System.getProperty(VM_ARGS_APP_PACKAGE);
    private static String appActivity = System.getProperty(VM_ARGS_APP_ACTIVITY);
    private static String cloudUsername, cloudAccessKey, cloudJobName, cloudBuildNumber;


    private static boolean isMobile = channel.equalsIgnoreCase("mobile") ? true : false;
    private static boolean isWeb = channel.equalsIgnoreCase("web") ? true : false;


    //initialization needed for Jenkins_SauceLabs plugin
    static {
        if (TestContext.getVendor().equals(TestContext.VendorType.SAUCELABS)) {
            cloudUsername = System.getenv(ENV_SAUCE_USERNAME);
            cloudAccessKey = System.getenv(ENV_SAUCE_ACCESS_KEY);
            cloudJobName = System.getenv("JOB_NAME");
            cloudBuildNumber = System.getenv("BUILD_NUMBER");

            if (cloudJobName == null) {
                cloudJobName = "IntelliJ";
                cloudBuildNumber = "DEV";
            }

            String envPlatform = System.getenv("SELENIUM_PLATFORM");
            if (envPlatform != null && isMobile) {
                platform = envPlatform + " " + System.getenv("SELENIUM_VERSION");
                device = System.getenv("SELENIUM_DEVICE");
                deviceOrientation = System.getenv("SELENIUM_DEVICE_ORIENTATION");
                browser = System.getenv("SELENIUM_BROWSER");
                if (browser.equals("android")) browser = "Browser";

            } else {
                platform = envPlatform;
                browserVersion = System.getenv("SELENIUM_VERSION");
                browser = System.getenv("SELENIUM_BROWSER");
            }
        }

        if (TestContext.getVendor().equals(TestContext.VendorType.BROWSERSTACK))
        {
            cloudUsername = System.getenv(ENV_BROWSER_STACK_USERNAME);
            cloudAccessKey = System.getenv(ENV_BROWSER_STACK_ACCESS_KEY);
            cloudJobName = System.getenv("BS_AUTOMATE_PROJECT");
            cloudBuildNumber = System.getenv("BS_AUTOMATE_BUILD");

            if (cloudJobName == null){
                cloudJobName = "IntelliJ";
                cloudBuildNumber = "DEV";
            }

            platform = System.getenv("SELENIUM_PLATFORM");
            browserVersion = System.getenv("SELENIUM_VERSION");
            browser = System.getenv("SELENIUM_BROWSER");

        }
    }


    private static String language;
    private static String scenario;

    private static String baseURL;
    private static Properties marketProperties;
    private static Properties envProperties;
    private static Properties userProperties;
    private static Properties paymentProperties;
    private static Properties kycProperties;
    //private static ReloadableResourceBundleMessageSource translationsBundle;

    private static SimpleDateFormat dateTime;
    private static GregorianCalendar today;

    //synchronizedPage might replace the current page - to be refactored later on
    //private static Widget synchronizedPage = null;
    //private static IGeneralPage currentPage;

    private static boolean skipScenario;

    /*
    @Autowired
    private ScenarioStore scenarioStore;
    */
    //@Autowired
    //private static Scenario cucumberScenario;

    public enum VendorType {
        AWS, SAUCELABS, APPIUM, UNIBET_QA, TESTOBJECT, PERFECTO_MOBILE, BROWSERSTACK
    }

    public enum MobileDevicePlatform {
        ANDROID, IOS
    }

    public enum DeviceOrientation {
        PORTRAIT, LANDSCAPE
    }

    public static void initContext() throws IOException {

        try {
            if (market!= null && market.contains("-")) {
                String arr[] = market.split("-");
                setJurisdiction(arr[0]);
                setMarket(arr[1]);
            }
            dateTime = new SimpleDateFormat("yyMMddHHmmssSS");
            logger.info("Brand=" + brand + " | Channel=" + channel + " | Env=" + env + " | Jurisdiction=" + jurisdiction + " | Market=" + market + " | Browser=" + browser);

            envProperties = loadPropertiesFile("/" + env + "/env.properties");
            // Load all property files
            setProperties();

        } catch (Exception e) {
            logger.error("Error while initializing properties...", e);
        }

        //TestContext.setSynchronizedPage(null);
    }

    private static void printProperties(Properties name, boolean toPrint) {
        if (!name.isEmpty()) {
            if (toPrint) {
                logger.info("Properties for " + name);
                for (Map.Entry<Object, Object> e : name.entrySet()) {
                    String key = (String) e.getKey();
                    String value = (String) e.getValue();
                    logger.info("Key=" + key + " Value=" + value);
                }
            }
        } else {
            logger.info("No properties found!");
        }
    }

    private static Properties loadPropertiesFile(String fileName) throws Exception {
        logger.info("Loading property file : " + fileName);
        Properties props = new Properties();
        props.load(TestContext.class.getResourceAsStream(fileName));
        printProperties(props, false);
        return props;
    }

    public static String getEnvProperty(String key) {
        logger.debug("Getting EnvProperty key: " + key);
        String value = envProperties.getProperty(key);
        return value;
    }

    public static void setProperties() throws Exception {
        PropertiesUtil propertiesUtil = new PropertiesUtil();
        //translationsBundle = propertiesUtil.loadTranslationsFiles();

        if (jurisdiction != null && market != null) {
            marketProperties = PropertiesUtil.loadPropertiesFile("/" + env + "/" + brand + "/market_" + jurisdiction + "_" + market + ".properties");
            baseURL = marketProperties.getProperty("baseURL");
            language = getMarketProperty("language");
            try { //might not be available for all the modules
                userProperties = PropertiesUtil.loadPropertiesFile("/data/users/" + env + "/user_" + jurisdiction + "_" + market + ".properties");
            } catch (Exception e) {
            }
        }

        paymentProperties = PropertiesUtil.loadPropertiesFile("/payment/payment.properties");
        kycProperties = PropertiesUtil.loadPropertiesFile("/kyc/kyc.properties");
    }

    public static void setScenario(String cukeScenario) {
        scenario = cukeScenario;
    }

    public static String getScenario() {
        return scenario;
    }

    public static String getCloudUsername() {
        return cloudUsername;
    }

    public static String getCloudAccessKey() {
        return cloudAccessKey;
    }

    public static String getCloudBuildNumber() {
        return cloudBuildNumber;
    }

    public static String getCloudJobName() {
        return cloudJobName;
    }

    public static String getChannel() {
        return channel;
    }

    public static void setChannel(String channel) {
        TestContext.channel = channel;
    }

    public static boolean isMobileChannel() {
        return isMobile;
    }

    public static boolean isWebChannel() {
        return isWeb;
    }

    public static boolean isAndroidPlatform() {
        return MobileDevicePlatform.ANDROID.equals(MobileDevicePlatform.valueOf(getPlatformName().toUpperCase()));
    }

    public static boolean isIOSPlatform() {
        return MobileDevicePlatform.IOS.equals(MobileDevicePlatform.valueOf(getPlatformName().toUpperCase()));
    }

    public static String getAppiumVersion() {
        return appiumVersion;
    }

    public static void setAppiumVersion(String appiumVersion) {
        TestContext.appiumVersion = appiumVersion;
    }

    public static boolean isRecordVideo() {
        return recordVideo;
    }

    public static void setRecordVideo(boolean recordVideo) {
        TestContext.recordVideo = recordVideo;
    }

    public static String getApp() {
        return app;
    }

    public static void setApp(String app) {
        TestContext.app = app;
    }

    public static String getDeviceId() {
        return deviceId;
    }

    public static void setDeviceId(String deviceId) {
        TestContext.deviceId = deviceId;
    }

    public static String getBundleId() {
        return bundleId;
    }

    public static void setBundleId(String bundleId) {
        TestContext.bundleId = bundleId;
    }

    public static void setPlatform(String p) {
        platform = p;
    }

    public static String getPlatformName() {
        return platform.split("\\s")[0];
    }

    public static String getPlatformVersion() {
        return platform.split("\\s")[1];
    }

    public static String getBrowserVersion() {
        if (TestContext.getVendor().equals(VendorType.BROWSERSTACK))
            return browser.split("\\s")[1];

        return browserVersion;
    }

    public static void setBrowserVersion(String browserVersion) {
        TestContext.browserVersion = browserVersion;
    }

    public static DeviceOrientation getDeviceOrientation() {
        if (deviceOrientation == null || (deviceOrientation != null && deviceOrientation.isEmpty()))
            return DeviceOrientation.PORTRAIT;
        return DeviceOrientation.valueOf(deviceOrientation.toUpperCase());
    }

    public static void setDeviceOrientation(DeviceOrientation deviceOrientation) {
        TestContext.deviceOrientation = deviceOrientation.toString().toUpperCase();
    }

    public static void setDevice(String dev) {
        device = dev;
    }

    public static String getDevice() {
        return device;
    }

    public static String getMarketProperty(String key) {
        logger.debug("Getting MarketProperty key: " + key);
        return marketProperties.getProperty(key);
    }

    public static String getUserProperty(String key) {
        logger.debug("Getting UserProperty key: " + key);
        return userProperties.getProperty(key);
    }

    /*
    public static Brand getBrand() {
        if (brand.equals(Brand.unibet.name()))
            return Brand.unibet;
        if (brand.equals(Brand.maria.name()))
            return Brand.maria;
        return null;
    }*/

    /*
    public static Jurisdiction getJurisdiction() {
        return Jurisdiction.getJurisdiction(jurisdiction);
    }
    */

    public static void setJurisdiction(String jurisdiction) {
        TestContext.jurisdiction = jurisdiction;
    }

    public static void setMarket(String market) {
        TestContext.market = market;
    }

    public static String getMarket() {
        return market;
    }

    public static void setVendor(VendorType vendor) {
        TestContext.vendor = vendor.toString().toUpperCase();
    }

    public static VendorType getVendor() {
        if (vendor == null || (vendor != null && vendor.isEmpty()))
            return VendorType.AWS;
        return VendorType.valueOf(vendor.toUpperCase());
    }

    public static String getTimeStamp() {
        today = new GregorianCalendar();
        return dateTime.format(today.getTime());
    }

    public static boolean isRunOnGrid() {
        return runOnGrid;
    }

    /*
    public static String getTranslation(String key) throws Exception {
        if (language == null)
            language = getMarketProperty("language");
        return translationsBundle.getMessage(key, null, new Locale(language));
    }
    */

    /*
    public static String getLanguage() {
        return language;
    }

    public static void setLanguage(String lang) {
        language = lang;
    }

    public static String getLocale() {
        return getMarketProperty("locale");
    }

    public static String getCountry() {
        return getMarketProperty("country");
    }

    public static String getCurrency() {
        return getMarketProperty("currency");
    }
    */

    /*
    public static Widget getSynchronizedPage() {
        return synchronizedPage;
    }

    public static void setSynchronizedPage(Widget synchronizedPage) {
        TestContext.synchronizedPage = synchronizedPage;
    }
    */

    /*
    //for payment - to be removed when we adopt the new model in Payment
    public static IGeneralPage getCurrentPage() {
        return currentPage;
    }

    public static void setCurrentPage(IGeneralPage page) {
        TestContext.currentPage = page;
    }
    */

    public static void setSkipScenario(boolean skipScenario) {
        TestContext.skipScenario = skipScenario;
    }

    public static String getEnv() {
        return env;
    }

    public static void setBrowser(String browserName) {
        browser = browserName;
    }

    public static String getBrowser() {
        if (TestContext.getVendor().equals(VendorType.BROWSERSTACK))
            return browser.split("\\s")[0];

        return browser;
    }

    public static void setBaseURL(String url) {
        baseURL = url;
    }

    public static String getBaseURL() {
        return baseURL;
    }

    public static String getPaymentProperty(String key) {
        return paymentProperties.getProperty(key);
    }

    public static String getKycProperty(String key) {
        return kycProperties.getProperty(key);
    }

    public static String getPlatform() {
        return platform;
    }

    public static String getTestName() {
        return testName;
    }

    public static void setTestName(String testName) {
        TestContext.testName = testName;
    }

    public static String getAppPackage() {
        return appPackage;
    }

    public static void setAppPackage(String appPackage) {
        TestContext.appPackage = appPackage;
    }

    public static String getAppActivity() {
        return appActivity;
    }

    public static void setAppActivity(String appActivity) {
        TestContext.appActivity = appActivity;
    }

    /**
     * This function is resolving the generic key (eg. ${user.valid.username} ) based on the User_<market> property file.
     * The User property file will be loaded by the TestContext based on the given market configuration for the test iteration.
     *
     * @param //generic - the entry key that will be searched in the property file
     *                If the key is not found then the function returns the parameter's content.
     * @return solved entry key based on the User property file that corresponds to the market under test.
     */
    /*
    public String parameterized(String generic) throws Exception {
        String key = General.getStringValueFromKeyParameter(generic);
        key = key.replace("${brand}", brand).replace("${market}", market).replace("${jurisdiction}", jurisdiction);
        String solvedKey = TestContext.getUserProperty(key);
        logger.info("Solve generic key=" + generic);
        if (solvedKey == null) {
            String brand = TestContext.getBrand().name().toLowerCase();
            if (generic.contains("." + brand)) {
                //Market change required -> extract the market and the jurisdiction
                String splittedKey[] = key.split(brand + ".");
                String keysArr[] = splittedKey[1].split("\\.");
                String jurisdiction = keysArr[0];
                String market;
                if (jurisdiction.equals("UK"))
                    market = keysArr[1] + "." + keysArr[2];
                else
                    market = keysArr[1];
                if (!TestContext.jurisdiction.equals(jurisdiction) || !TestContext.market.equals(market)) {
                    TestContext.setJurisdiction(jurisdiction);
                    TestContext.setMarket(market);
                    TestContext.initContext();
                }
                solvedKey = parameterized(generic);
                return solvedKey;
            } else
                solvedKey = generic;
        } else
            scenarioStore.save(generic, solvedKey);
        return solvedKey;
    }
    */

    /*
    public void saveDateTime() {
        scenarioStore.save("EXECUTION_DATE", FormatUtil.getFormattedDate(new Date()));
    }
    */

    /*
    public void saveMarket() {
        //Jurisdiction jurisdiction = TestContext.getJurisdiction();
        //String market = TestContext.getMarket();

        if (jurisdiction != null) {
            StringBuilder jurisdictionSB = new StringBuilder();
            jurisdictionSB.append(brand.toUpperCase());
            jurisdictionSB.append(" - " + jurisdiction);
            scenarioStore.save("JURISDICTION", jurisdictionSB.toString());
        }

        if (market != null) {
            StringBuilder marketSB = new StringBuilder();
            marketSB.append(brand.toUpperCase());
            marketSB.append(" - " + TestContext.getMarket());

            scenarioStore.save("MARKET", marketSB.toString());
        }
    }*/

}
