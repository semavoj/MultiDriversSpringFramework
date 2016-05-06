package automationFramework;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pageObjects.Home_Page;
import appModules.SignIn_Action;

/**
 * Created by Marinko on 2016-05-02.
 */
public class Module_TC {
    public static WebDriver driver = null;

    public static void main(String[] args){

        driver = new FirefoxDriver();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("http://www.store.demoqa.com");

        // Use page Object library now

        try {
            SignIn_Action.Execute(driver);

        } catch (Exception e)
        {
            System.out.println("Sign_In_error");
        }
        System.out.println(" Login Successfully, now it is the time to Log Off buddy.");

        try {

            Home_Page.lnk_LogOut(driver).click();

        } catch (Exception e){
            System.out.println("Log_out error");
        }
        driver.quit();
    }
}
