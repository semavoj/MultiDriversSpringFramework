package appModules;

import org.openqa.selenium.WebDriver;
import pageObjects.HomePage;
import pageObjects.LogIn_Page;

/**
 * Created by Marinko on 2016-05-02.
 */
public class SignIn_Action {

    public static void Execute(WebDriver driver, String sUsername, String sPassword){

        HomePage.lnk_SignIn(driver).click();

        LogIn_Page.txtbx_UserName(driver).sendKeys(sUsername);

        LogIn_Page.txtbx_Password(driver).sendKeys(sPassword);

        LogIn_Page.btn_LogIn(driver).click();
    }
}
