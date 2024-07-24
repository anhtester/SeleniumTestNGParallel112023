package com.anhtester.Bai28_ExcelFileData.testcases;

import com.anhtester.Bai28_ExcelFileData.pages.LoginPage;
import com.anhtester.common.BaseTest;
import com.anhtester.helpers.ExcelHelper;
import com.anhtester.keywords.WebUI;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    private LoginPage loginPage;

    @Test
    public void testLoginSuccess() {
        loginPage = new LoginPage();

        ExcelHelper excelHelper = new ExcelHelper();
        excelHelper.setExcelFile("src/test/resources/testdata/Login.xlsx", "Sheet3");

        for (int i = 1; i <= 2; i++) {
            loginPage.loginCRM(
                    excelHelper.getCellData("EMAIL", i),
                    excelHelper.getCellData("PASSWORD", i)
            );
            loginPage.verifyLoginSuccess();
            loginPage.logout();
        }
        excelHelper.setCellData("Passed", "STATUS", 1);
    }

    @Test
    public void testLoginFailWithEmailInvalid() {
        loginPage = new LoginPage();

        ExcelHelper excelHelper = new ExcelHelper();
        excelHelper.setExcelFile("src/test/resources/testdata/Login.xlsx", "Sheet1");

        loginPage.loginCRM(
                excelHelper.getCellData("EMAIL", 2),
                excelHelper.getCellData("PASSWORD", 2)
        );
        loginPage.verifyLoginFail("Invalid email or password");
        excelHelper.setCellData("Passed", "STATUS", 1);
    }

    @Test
    public void testLoginFailWithPasswordInvalid() {
        loginPage = new LoginPage();

        ExcelHelper excelHelper = new ExcelHelper();
        excelHelper.setExcelFile("src/test/resources/testdata/Login.xlsx", "Sheet1");

        loginPage.loginCRM(
                excelHelper.getCellData("EMAIL", 3),
                excelHelper.getCellData("PASSWORD", 3)
        );
        loginPage.verifyLoginFail("Invalid email or password");
        excelHelper.setCellData("Passed", "STATUS", 2);
    }

    @Test
    public void testLoginFailWithEmailNull() {
        loginPage = new LoginPage();
        ExcelHelper excelHelper = new ExcelHelper();
        excelHelper.setExcelFile("src/test/resources/testdata/Login.xlsx", "Sheet1");

        loginPage.loginCRM("", "123");
        loginPage.verifyLoginFail("The Email Address field is required.");
        excelHelper.setCellData("Passed", "STATUS", 3);
    }

    @Test
    public void testLoginFailWithPasswordNull() {
        loginPage = new LoginPage();
        ExcelHelper excelHelper = new ExcelHelper();
        excelHelper.setExcelFile("src/test/resources/testdata/Login.xlsx", "Sheet1");

        loginPage.loginCRM("admin@example.com", "");
        loginPage.verifyLoginFail("The Password field is required.");
        WebUI.sleep(1);
        excelHelper.setCellData("Passed", "STATUS", 4);
    }
}
