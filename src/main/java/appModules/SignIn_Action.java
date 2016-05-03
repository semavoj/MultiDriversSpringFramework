package appModules;

import org.apache.commons.logging.Log;
import org.openqa.selenium.WebDriver;
import pageObjects.HomePage;
import pageObjects.LogIn_Page;
import utility.ExcelUtils;

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

        String sPassWord = ExcelUtils.getCellData(2,3);

        HomePage.lnk_MyAccount(driver).click();

        HomePage.lnk_MyAccount(driver).click();

        LogIn_Page.txtbx_UserName(driver).sendKeys(sUserName);

        LogIn_Page.txtbx_Password(driver).sendKeys(sPassWord);

        LogIn_Page.btn_LogIn(driver).click();
    }
}
