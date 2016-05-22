package com.framework.driver.webdriver;

import com.framework.core.utils.TestContext;
import com.framework.driver.webdriver.utils.OSArch;
import com.framework.driver.webdriver.utils.StringUtils;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.remote.service.DriverService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URISyntaxException;

/**
 * Created by Marinko on 2016-05-21.
 */
public class SeleniumServerConfig {    private final String serverLocation;
    private final String SERVER_BUNDLE_LOCATION = "/bundleddrivers/";
    private final String browser = TestContext.getBrowser();

    private final static Logger logger = LoggerFactory.getLogger(SeleniumServerConfig.class);

    public SeleniumServerConfig(String serverLocation) {
        this.serverLocation = serverLocation;
    }

    public DriverService getService() {
        DriverService driverService = createDriverService();
        startDriverService(driverService);
        return driverService;
    }

    private void startDriverService(DriverService driverService) {
        try {
            driverService.start();
        }
        catch (IOException e) {
            String errorMessage = "Error starting the Chrome driver at " + serverLocation + ". Ensure that you have permission to execute the driver.";
            System.err.println(errorMessage);
            throw new RuntimeException(errorMessage, e);
        }
    }

    private DriverService createDriverService() {
        File executableFile = getExecutableFile();
        if (executableFile != null) {

            DriverService.Builder builder = null;
            if(browser.equalsIgnoreCase("chrome")) {
                builder = new ChromeDriverService.Builder();
            } else if (browser.equalsIgnoreCase("ie")) {
                builder = new InternetExplorerDriverService.Builder();
            }

            builder.usingAnyFreePort();
            builder.usingDriverExecutable(executableFile);
            return builder.build();
        }

        return ChromeDriverService.createDefaultService();
    }

    private File getExecutableFile() {
        OSArch osArch = getOSArchServerBinary();
        File serverBinary = null;
        if (isBinaryPathProvidedInUnitardProperties()) {
            serverBinary = useCustomBinary(osArch);
        } else if ((!isBinaryPathSetAsSystemProperty()) &&
                (osArch != OSArch.NONE)) {
            serverBinary = useBundledBinary(osArch);
        }

        return serverBinary;
    }

    private boolean isBinaryPathProvidedInUnitardProperties() {
        return !StringUtils.isEmpty(this.serverLocation);
    }

    private boolean isBinaryPathSetAsSystemProperty() {
        return !StringUtils.isEmpty(System.getProperty("webdriver.chrome.driver"));
    }

    private File useCustomBinary(OSArch osArch) {

        File customBinary = new File(serverLocation);

        if(customBinary.exists()){
            logger.debug("Binary found at: " + serverLocation);
        } else {
            logger.debug("File not found! Copying bundled binary!");
            customBinary = useBundledBinary(osArch);
        }

        return customBinary;
    }

    private File useBundledBinary(OSArch osArch)
    {
        try {
            File serverBinary = getServerBinary(osArch);
            osArch.makeBinaryExecutable(serverBinary);
            logger.info("Bundled binary: " + serverBinary);
            return serverBinary;
        }
        catch (URISyntaxException e) {
            throw new RuntimeException("Error deriving chrome binary", e);
        }
    }

    private File getServerBinary(OSArch osArch) throws URISyntaxException {

        InputStream inputStream = null;
        OutputStream outputStream = null;

        String targetDriver = null;

        if(browser.equalsIgnoreCase("chrome")) {
            targetDriver = "chromedriver" + osArch.chromeBinary();
        } else {
            targetDriver = "IEDriverServer_" + osArch.internetExplorerBinary();
        }

        File driversFolder = new File("drivers");

        if(driversFolder.exists()) {
            logger.debug("Folder for drivers already exists.");
        } else {
            boolean created = driversFolder.mkdir();
            logger.debug("Driver folder created (" + String.valueOf(created) + ")");
        }

        String driverLocation = System.getProperty("user.dir") + "/drivers/" + targetDriver;

        File driverBinary = new File(driverLocation);

        if(!driverBinary.exists()) {
            try {
                logger.debug(String.valueOf(SeleniumServerConfig.class
                        .getResource(SERVER_BUNDLE_LOCATION + targetDriver)));
                inputStream = SeleniumServerConfig.class.getResourceAsStream(SERVER_BUNDLE_LOCATION + targetDriver);
                outputStream = new FileOutputStream(new File(driverLocation));

                int read;

                byte[] bytes = new byte[1024];

                while ((read = inputStream.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, read);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // Clean up the streams
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return  driverBinary;
    }

    private OSArch getOSArchServerBinary() {
        return OSArch.forOSArch(System.getProperty("os.name"), System.getProperty("os.arch"));
    }
}
