package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utility.Log;

/**
 * Created by Marinko on 2016-05-02.
 */
public class HomePage {
    public static WebElement element = null;

    public static WebElement lnk_SignIn(WebDriver driver){

        element = driver.findElement(By.id("account"));

        Log.info("Account link found");
        return element;
    }

    public static WebElement lnk_MyAccount(WebDriver driver){

        element = driver.findElement(By.id("account"));

        Log.info("My account link element found");

        return element;
    }

    public static WebElement lnk_LogOut(WebDriver driver){

        element = driver.findElement(By.id("account_logout"));

        Log.info("Log Out link element found");

        return element;
    }
}
