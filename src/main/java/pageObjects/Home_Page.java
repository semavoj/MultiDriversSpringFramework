package pageObjects;

import org.apache.poi.hpsf.Util;
import org.openqa.selenium.*;
import utility.Log;
import utility.Utils;

/**
 * Created by Marinko on 2016-05-02.
 */
public class Home_Page {
    public static WebElement element = null;

    public static WebElement lnk_SignIn(WebDriver driver) throws Exception {

        try {

            element = Utils.fluentWait(driver, By.id("account"));

        } catch (NotFoundException nfe){

            Log.error("My account link not found");

            throw(nfe);

        } catch (TimeoutException toe){

            Log.error("My account link timeout");

            throw (toe);
        }

        Log.info("Account link found");

        return element;
    }

    public static WebElement lnk_MyAccount(WebDriver driver) throws Exception {

        try {

            element = Utils.fluentWait(driver, By.id("accounts"));
            element.click();

        } catch (NotFoundException nfe){

            Log.error("My account link not found");

            throw (nfe);

        } catch (TimeoutException toe){

            Log.error("My account link timeout");

            throw(toe);
        }

        Log.info("My account link element found");

        return element;
    }

    public static WebElement lnk_LogOut(WebDriver driver) throws Exception{

        try {
            element = Utils.fluentWait(driver, By.id("account_logout"));
            //driver.findElement(By.id("account_logout"));

        } catch (NotFoundException nfe)
        {
            Log.error("Logout link not found");

            throw (nfe);
        } catch (TimeoutException toe){

            Log.error("Logout link timeo out");

            throw (toe);
        }

        Log.info("Log Out link element found");

        return element;
    }
}
