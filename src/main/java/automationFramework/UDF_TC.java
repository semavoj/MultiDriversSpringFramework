package automationFramework;

import appModules.SignIn_Action;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.Home_Page;
import pageObjects.ProductListing_Page;
import utility.Constant;
import utility.ExcelUtils;
import utility.Log;
import utility.Utils;

/**
 * Created by Marinko on 2016-05-03.
 */
public class UDF_TC{

    public WebDriver driver;

    private String sTestCaseName;

    private int iTestCaseRow;

    @BeforeMethod

    public void beforeMethod() throws Exception {

        sTestCaseName = this.toString();

        sTestCaseName = Utils.getTestCaseName(this.toString());

        Log.startTestCase(sTestCaseName);

        ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData,Constant.SheetName);

        iTestCaseRow = ExcelUtils.getRowContains(sTestCaseName,Constant.Col_TestCaseName);

        driver = Utils.openBrowser(iTestCaseRow);
    }

    @Test

    public void main() throws Exception {

        SignIn_Action.Execute(driver, Constant.Username, Constant.Password);

        System.out.println("Login Successfully, now it is the time to Log Off buddy.");

        //ProductListing_Page.Product_1.img_Product(driver).click();

        Home_Page.lnk_LogOut(driver).click();

        Log.info("Click action is perfomred on Log Out link");

    }

    @AfterMethod

    public void afterMethod() {

        //Quit and close
        driver.quit();

    }

}