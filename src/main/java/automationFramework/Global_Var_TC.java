package automationFramework;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pageObjects.HomePage;
import appModules.SignIn_Action;

// Import package utility.*

import utility.Constant;
/**
 * Created by Marinko on 2016-05-02.
 */
public class Global_Var_TC {

    private static WebDriver driver = null;

    public static void main(String[] args) {

        driver = new FirefoxDriver();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // Launch the Online Store Website using Constant Variable

        driver.get(Constant.URL);

        // Pass Constant Variables as arguments to Execute method

        SignIn_Action.Execute(driver,Constant.Username,Constant.Password);

        System.out.println("Login Successfully, now it is the time to Log Off buddy.");

        HomePage.lnk_LogOut(driver).click();

        driver.quit();

    }
}
