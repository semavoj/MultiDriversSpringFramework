package pageObjects;

import org.apache.poi.hpsf.Util;
import org.openqa.selenium.*;
import utility.Log;
import utility.Utils;

/**
 * Created by Marinko on 2016-05-02.
 */
public class Home_Page extends BaseClass{

    public static WebElement element = null;


    public Home_Page(WebDriver driver){

        super(driver);

    }

    public static WebElement lnk_SignIn() throws Exception {

        try {

            element = driver.findElement(By.id("account"));
            //element = Utils.fluentWait(driver, By.id("account"));

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

    public static WebElement lnk_MyAccount() throws Exception {

        try {

            element = driver.findElement(By.id("account"));
            //element = Utils.fluentWait(driver, By.id("accounts"));
            //element.click();

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

    public static WebElement lnk_LogOut() throws Exception{

        try {

            element = driver.findElement(By.id("account_logout"));
            //element = Utils.fluentWait(driver, By.id("account_logout"));
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

    public static class TopNavigation {

        public static class Product_Type {

            static WebElement mainElement;

            public static void Accessories() throws Exception {

                try {

                    mainElement = driver.findElement(By.linkText("Product Category"));

                    Log.info("Product category link is found under Top Navigation");

                    Utils.mouseHoverAction(mainElement, "Accessories");

                } catch (Exception e) {

                    Log.error("Accessories link is not found under Product Category");

                    throw (e);

                }

            }

            public static void iMacs() throws Exception {

                try {

                    mainElement = driver.findElement(By.linkText("Product Category"));

                    Log.info("Product category link is found under Top Navigation");

                    Utils.mouseHoverAction(mainElement, "iMacs");

                } catch (Exception e) {

                    Log.error("Accessories link is not found under Product Category");

                    throw (e);

                }

            }

            public static void iPads() throws Exception {

                try {

                    mainElement = driver.findElement(By.linkText("Product Category"));

                    Log.info("Product category link is found under Top Navigation");

                    Utils.mouseHoverAction(mainElement, "iPads");

                } catch (Exception e) {

                    Log.error("Accessories link is not found under Product Category");

                    throw (e);

                }

            }

            public static void iPhones() throws Exception {

                try {

                    mainElement = driver.findElement(By.linkText("Product Category"));

                    Log.info("Product category link is found under Top Navigation");

                    Utils.mouseHoverAction(mainElement, "iPhones");

                } catch (Exception e) {

                    Log.error("Accessories link is not found under Product Category");

                    throw (e);

                }

            }
        }
    }
}
