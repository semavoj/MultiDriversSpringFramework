package com.framework.driver;

import com.framework.driver.webdriver.utils.PathUtils;
import com.framework.driver.webdriver.utils.StringUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;

/**
 * Created by Marinko on 2016-05-22.
 */
public interface Profile {

    FirefoxProfile firefoxProfile(boolean paramBoolean);

    void setPreferences(FirefoxProfile paramFirefoxProfile);

    Capabilities DesiredCapabilities();

    class NoProfile
            implements Profile
    {
        public FirefoxProfile firefoxProfile(boolean enableNativeEvents)
        {
            return new FirefoxProfile();
        }

        public void setPreferences(FirefoxProfile firefoxProfile) {}

        public Capabilities DesiredCapabilities()
        {
            return new DesiredCapabilities();
        }
    }

    class SimpleProfile
            implements Profile
    {
        private final String profilePath;
        private Profile nextProfile = new Profile.NoProfile();

        public SimpleProfile(String profilePath)
        {
            this.profilePath = profilePath;
        }

        public FirefoxProfile firefoxProfile(boolean enableNativeEvents)
        {
            FirefoxProfile firefoxProfile = getFirefoxProfile(PathUtils.escapeSpecialCharsInPath(this.profilePath), enableNativeEvents);
            if (enableNativeEvents) {
                firefoxProfile.setEnableNativeEvents(true);
            }
            return firefoxProfile;
        }

        private FirefoxProfile getFirefoxProfile(String profilePath, boolean shouldEnableNativeEvents)
        {
            if (StringUtils.isEmpty(profilePath)) {
                return this.nextProfile.firefoxProfile(shouldEnableNativeEvents);
            }
            FirefoxProfile firefoxProfile = new FirefoxProfile(new File(profilePath));
            this.nextProfile.setPreferences(firefoxProfile);
            return firefoxProfile;
        }

        public void setPreferences(FirefoxProfile firefoxProfile) {}

        public Capabilities DesiredCapabilities()
        {
            return new DesiredCapabilities();
        }
    }

}
