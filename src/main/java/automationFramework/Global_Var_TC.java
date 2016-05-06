package automationFramework;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pageObjects.Home_Page;
import appModules.SignIn_Action;
import org.apache.log4j.Logger;

// Import package utility.*

import utility.Constant;
import utility.Log;

/**
 * Created by Marinko on 2016-05-02.
 */
public class Global_Var_TC {

    final static Logger logger = Logger.getLogger(Global_Var_TC.class);

    private static WebDriver driver = null;

    public static void main(String[] args) {

        driver = new FirefoxDriver();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // Launch the Online Store Website using Constant Variable

        driver.get(Constant.URL);

        // Pass Constant Variables as arguments to Execute method

        try {
            SignIn_Action.Execute(Constant.Username, Constant.Password);
        } catch (Exception e){
            Log.error("");
        }
        logger.info("Login Successfully, now it is the time to Log Off buddy.");
        //System.out.println("Login Successfully, now it is the time to Log Off buddy.");

        try {
            Home_Page.lnk_LogOut().click();
        } catch (Exception e){
            Log.error("");
        }

        driver.quit();

    }
}
