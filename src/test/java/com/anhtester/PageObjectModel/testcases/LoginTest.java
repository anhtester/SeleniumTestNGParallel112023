package com.anhtester.PageObjectModel.testcases;

import com.anhtester.PageObjectModel.pages.LoginPage;
import com.anhtester.common.BaseTest;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    private LoginPage loginPage;

    @Test
    public void testLoginSuccess(){
        loginPage = new LoginPage(driver);

        loginPage.loginCRM("admin@example.com", "123456");
        loginPage.verifyLoginSuccess();
    }

    @Test
    public void testLoginFailWithEmailInvalid(){
        loginPage = new LoginPage(driver);

        loginPage.loginCRM("admin123@example.com", "123456");
        loginPage.verifyLoginFail("Invalid email or password");
    }

    @Test
    public void testLoginFailWithPasswordInvalid(){
        loginPage = new LoginPage(driver);

        loginPage.loginCRM("admin@example.com", "123");
        loginPage.verifyLoginFail("Invalid email or password");
    }

    @Test
    public void testLoginFailWithEmailNull(){
        loginPage = new LoginPage(driver);

        loginPage.loginCRM("", "123");
        loginPage.verifyLoginFail("The Email Address field is required.");
    }

    @Test
    public void testLoginFailWithPasswordNull(){
        loginPage = new LoginPage(driver);

        loginPage.loginCRM("admin@example.com", "");
        loginPage.verifyLoginFail("The Password field is required.");
    }
}
