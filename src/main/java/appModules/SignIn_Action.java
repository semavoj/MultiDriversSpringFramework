package appModules;

import org.testng.Reporter;
import pageObjects.Home_Page;
import pageObjects.LogIn_Page;
import utility.Constant;
import utility.ExcelUtils;
import utility.Log;
import utility.Utils;

/**
 * Created by Marinko on 2016-05-02.
 */
public class SignIn_Action {

    public static void Execute(String sUsername, String sPassword) throws Exception{

        Home_Page.lnk_SignIn().click();

        LogIn_Page.txtbx_UserName().sendKeys(sUsername);

        LogIn_Page.txtbx_Password().sendKeys(sPassword);

        LogIn_Page.btn_LogIn().click();
    }

    public static void Execute() throws Exception{

        String sUserName = ExcelUtils.getCellData(2,2);

        Log.info("Username picked from Excel is" + sUserName);

        String sPassWord = ExcelUtils.getCellData(2,3);

        Log.info("Password picked from Excel is" + sPassWord);

        Home_Page.lnk_MyAccount().click();

        Log.info("Click action performed on My Account link");

        LogIn_Page.txtbx_UserName().sendKeys(sUserName);

        Log.info("Username entered in the Username text box");

        LogIn_Page.txtbx_Password().sendKeys(sPassWord);

        Log.info("Password entered in the Password text box");

        LogIn_Page.btn_LogIn().click();

        Log.info("Click action performed on Submit button");
    }

    public static void Execute(int iTestCaseRow) throws Exception{

        // Clicking on the My Account link on the Home Page

        Home_Page.lnk_MyAccount().click();

        Log.info("Click action is perfromed on My Account link" );

        // Storing the UserName in to a String variable and Getting the UserName from Test Data Excel sheet

        // iTestcaseRow is the row number of our Testcase name in the Test Data sheet

        // Constant.Col_UserName is the column number for UserName column in the Test Data sheet

        // Please see the Constant class in the Utility Package

        // For Use of Constant Variables, please see http://toolsqa.com/selenium-webdriver/constant-variables/

        String sUserName = ExcelUtils.getCellData(iTestCaseRow, Constant.Col_UserName);

        // Here we are sending the UserName string to the UserName Textbox on the LogIN Page

        // This is call Page Object Model (POM)

        // For use of POM, please see http://toolsqa.com/selenium-webdriver/page-object-model/

        LogIn_Page.txtbx_UserName().sendKeys(sUserName);

        // Printing the logs for what we have just performed

        Log.info(sUserName+" is entered in UserName text box" );

        String sPassword = ExcelUtils.getCellData(iTestCaseRow, Constant.Col_Password);

        LogIn_Page.txtbx_Password().sendKeys(sPassword);

        Log.info(sPassword+" is entered in Password text box" );

        LogIn_Page.btn_LogIn().click();

        Log.info("Click action is performed on Submit button");

        // I noticed in few runs that Selenium is trying to perform the next action before the complete Page load

        // So I have decided to put a wait on the Logout link element

        // Now it will wait 10 secs separately before jumping out to next step


        Utils.waitForElement(Home_Page.lnk_LogOut());

        // This is another type of logging, with the help of TestNg Reporter log

        // This has to be very carefully used, you should only print the very important message in to this

        // This will populate the logs in the TestNG HTML reports

        // I have used this Reporter log just once in this whole module

        Reporter.log("SignIn Action is successfully perfomred");

    }

}
