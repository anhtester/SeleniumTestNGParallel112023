package com.anhtester.Bai29_DataProvider.pages;

import com.anhtester.constants.ConfigData;
import com.anhtester.drivers.DriverManager;
import com.anhtester.keywords.WebUI;
import org.openqa.selenium.By;
import org.testng.Assert;

public class LoginPage extends CommonPage {
    private String URL = "https://crm.anhtester.com/admin/authentication";

    //Hàm xây dựng cho từng class Page
    public LoginPage() {
    }

    //Khai báo các element dạng đối tượng By
    private By headerPage = By.xpath("//h1[normalize-space()='Login']");
    private By inputEmail = By.xpath("//input[@id='email']");
    private By inputPassword = By.xpath("//input[@id='password']");
    private By buttonLogin = By.xpath("//button[normalize-space()='Login']");
    private By errorMessage = By.xpath("//div[contains(@class,'alert alert-danger')]");

    private By menuDashboard = By.xpath("//span[normalize-space()='Dashboard']");

    //Khai báo các hàm xử lý thuộc trang Login
    public void enterEmail(String email){
        //driver.findElement(inputEmail).sendKeys(email);
        WebUI.setText(inputEmail, email);
    }

    public void enterPassword(String password){
        //driver.findElement(inputPassword).sendKeys(password);
        WebUI.setText(inputPassword, password);
    }

    public void clickLoginButton(){
        //driver.findElement(buttonLogin).click();
        WebUI.clickElement(buttonLogin);
    }

    public DashboardPage loginCRM(String email, String password) {
        //driver.get(ConfigData.URL);
        WebUI.openURL(ConfigData.URL);
        WebUI.waitForPageLoaded();
        enterEmail(email);
        enterPassword(password);
        clickLoginButton();

        return new DashboardPage();
    }

    public void verifyLoginSuccess() {
        WebUI.waitForPageLoaded();
        Assert.assertTrue(WebUI.checkElementExist(menuDashboard), "\uD83D\uDC1E FAIL!! Can not redirect to Dashboard page.");
        Assert.assertEquals(DriverManager.getDriver().getCurrentUrl(), "https://crm.anhtester.com/admin/", "\uD83D\uDC1E FAIL!! The current url not match.");
    }

    public void verifyLoginFail(String expectedMessage) {
        WebUI.waitForPageLoaded();
        Assert.assertTrue(WebUI.checkElementExist(errorMessage), "\uD83D\uDC1E FAIL!! The error message not display.");
        Assert.assertEquals(WebUI.getElementText(errorMessage), expectedMessage, "\uD83D\uDC1E FAIL!! The content of error massge not match.");
    }
}
