package automationFramework;

import appModules.SignIn_Action;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pageObjects.HomePage;

import java.util.concurrent.TimeUnit;

/**
 * Created by Marinko on 2016-05-02.
 */
public class Param_TC {
    public static WebDriver driver = null;

    public static void main(String[] args) {

        driver = new FirefoxDriver();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("http://www.store.demoqa.com");

        // Use page Object library now

        SignIn_Action.Execute(driver, "testuser_1", "Test@123");

        System.out.println(" Login Successfully, now it is the time to Log Off buddy.");

        HomePage.lnk_LogOut(driver).click();

        driver.quit();
    }
}