package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utility.Log;

/**
 * Created by Marinko on 2016-05-02.
 */
public class LogIn_Page extends BaseClass {

    public static WebElement element = null;

    public LogIn_Page(WebDriver driver){

        super(driver);

    }

    public static WebElement txtbx_UserName() throws Exception{

        try {
            element = driver.findElement(By.id("log"));

            Log.info("Username text box found");
        } catch (Exception e){


            Log.error("UserName text box is not found on the Login Page");

            throw(e);
        }
        return element;
    }

    public static WebElement txtbx_Password() throws Exception{

        try {
            element = driver.findElement(By.id("pwd"));

            Log.info("Submit button found");
        } catch (Exception e){


            Log.error("Password text box is not found on the Login Page");

            throw (e);

        }
        return element;
    }

    public static WebElement btn_LogIn() throws Exception{

        try {
            element = driver.findElement(By.id("login"));

            Log.info("Submit button found");
        } catch (Exception e){

            Log.error("Submit button is not found on the Login Page");

            throw(e);
        }
        return element;
    }
}
