package appModules;

import org.openqa.selenium.WebDriver;
import pageObjects.HomePage;
import pageObjects.LogIn_Page;
import utility.ExcelUtils;
import utility.Log;

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

    public static void Execute(WebDriver driver) throws Exception{

        String sUserName = ExcelUtils.getCellData(2,2);

        Log.info("Username picked from Excel is" + sUserName);

        String sPassWord = ExcelUtils.getCellData(2,3);

        Log.info("Password picked from Excel is" + sPassWord);

        HomePage.lnk_MyAccount(driver).click();

        Log.info("Click action performed on My Account link");

        LogIn_Page.txtbx_UserName(driver).sendKeys(sUserName);

        Log.info("Username entered in the Username text box");

        LogIn_Page.txtbx_Password(driver).sendKeys(sPassWord);

        Log.info("Password entered in the Password text box");

        LogIn_Page.btn_LogIn(driver).click();

        Log.info("Click action performed on Submit button");
    }
}
