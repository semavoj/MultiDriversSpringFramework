package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by Marinko on 2016-05-02.
 */
public class LogIn_Page {
    public static WebElement element = null;

    public static WebElement txtbx_UserName(WebDriver driver){
        element = driver.findElement(By.id("log"));

        return element;
    }

    public static WebElement txtbx_Password(WebDriver driver){
        element = driver.findElement(By.id("pwd"));

        return element;
    }

    public static WebElement btn_LogIn(WebDriver driver){
        element = driver.findElement(By.id("login"));

        return element;
    }
}