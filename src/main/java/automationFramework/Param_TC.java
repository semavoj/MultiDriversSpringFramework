package automationFramework;

import appModules.SignIn_Action;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pageObjects.Home_Page;

import java.util.concurrent.TimeUnit;

/**
 * Created by Marinko on 2016-05-02.
 */
public class Param_TC {
    public static WebDriver driver = null;

    public static void main(String[] args) throws Exception{

        driver = new FirefoxDriver();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("http://www.store.demoqa.com");

        // Use page Object library now

        SignIn_Action.Execute( "testuser_1", "Test@123");

        System.out.println(" Login Successfully, now it is the time to Log Off buddy.");

        Home_Page.lnk_LogOut().click();

        driver.quit();
    }
}
