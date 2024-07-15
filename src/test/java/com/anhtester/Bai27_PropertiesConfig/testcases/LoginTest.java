package com.anhtester.Bai27_PropertiesConfig.testcases;

import com.anhtester.Bai27_PropertiesConfig.pages.LoginPage;
import com.anhtester.common.BaseTest;
import com.anhtester.constants.ConfigData;
import com.anhtester.helpers.PropertiesHelper;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    private LoginPage loginPage;

    @Test
    public void testLoginSuccess() {
        loginPage = new LoginPage();

        loginPage.loginCRM(ConfigData.EMAIL, ConfigData.PASSWORD);
        loginPage.verifyLoginSuccess();

        PropertiesHelper.setValue("ACTIVE", "true");
    }

    @Test
    public void testLoginFailWithEmailInvalid() {
        loginPage = new LoginPage();

        loginPage.loginCRM("admin123@example.com", "123456");
        loginPage.verifyLoginFail("Invalid email or password");
    }

    @Test
    public void testLoginFailWithPasswordInvalid() {
        loginPage = new LoginPage();

        loginPage.loginCRM("admin@example.com", "123");
        loginPage.verifyLoginFail("Invalid email or password");
    }

    @Test
    public void testLoginFailWithEmailNull() {
        loginPage = new LoginPage();

        loginPage.loginCRM("", "123");
        loginPage.verifyLoginFail("The Email Address field is required.");
    }

    @Test
    public void testLoginFailWithPasswordNull() {
        loginPage = new LoginPage();

        loginPage.loginCRM("admin@example.com", "");
        loginPage.verifyLoginFail("The Password field is required.");
    }
}
