package com.anhtester.Bai27_PropertiesConfig.pages;

import com.anhtester.drivers.DriverManager;
import com.anhtester.keywords.WebUI;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class DashboardPage extends CommonPage {

    SoftAssert softAssert = new SoftAssert();

    public DashboardPage() {
    }

    private By buttonDashboardOptions = By.xpath("//div[normalize-space()='Dashboard Options']");
    private By totalInvoicesAwaitingPayment = By.xpath("(//span[normalize-space()='Invoices Awaiting Payment']/parent::div)/following-sibling::span");
    private By totalConvertedLeads = By.xpath("(//span[normalize-space()='Converted Leads']/parent::div)/following-sibling::span");
    private By totalProjectsInProgress = By.xpath("(//span[normalize-space()='Projects In Progress']/parent::div)/following-sibling::span");
    private By totalTasksNotFinished = By.xpath("(//span[normalize-space()='Tasks Not Finished']/parent::div)/following-sibling::span");
    private By checkboxQuickStatistics = By.xpath("//input[@id='widget_option_top_stats']");
    private By sectionQuickStatistics = By.xpath("//div[@id='widget-top_stats']");

    public void clickButtonDashboardOptions(){
        WebUI.waitForPageLoaded();
        System.out.println(WebUI.checkElementExist(buttonDashboardOptions));
        WebUI.clickElement(buttonDashboardOptions);
    }

    public void verifyCheckboxQuickStatistics(){
        WebUI.sleep(1);
        Assert.assertTrue(DriverManager.getDriver().findElement(checkboxQuickStatistics).isSelected(), "FAIL!! The value of checkbox Quick Statistics not match.");
        Assert.assertTrue(DriverManager.getDriver().findElement(sectionQuickStatistics).isDisplayed(), "FAI!! The section Quick Statistics not display.");
    }

    public void checkTotalInvoicesAwaitingPayment(String value) {
        WebUI.waitForPageLoaded();
        softAssert.assertTrue(WebUI.checkElementExist(totalInvoicesAwaitingPayment), "The section Invoices Awaiting Payment not display.");
        softAssert.assertEquals(DriverManager.getDriver().findElement(totalInvoicesAwaitingPayment).getText(), value, "FAIL!! Invoices Awaiting Payment total not match.");
    }

    public void checkTotalConvertedLeads(String value) {
        WebUI.waitForPageLoaded();
        softAssert.assertTrue(WebUI.checkElementExist(totalConvertedLeads), "The section Converted Leads not display.");
        softAssert.assertEquals(DriverManager.getDriver().findElement(totalConvertedLeads).getText(), value, "FAIL!! Converted Leads total not match.");
    }

    public void checkTotalProjectsInProgress(String value) {
        WebUI.waitForPageLoaded();
        softAssert.assertTrue(WebUI.checkElementExist(totalProjectsInProgress), "The section Projects In Progress not display.");
        softAssert.assertEquals(DriverManager.getDriver().findElement(totalProjectsInProgress).getText(), value, "FAIL!! Projects In Progress total not match.");
    }

    public void checkTotalTasksNotFinished(String value) {
        WebUI.waitForPageLoaded();
        softAssert.assertTrue(WebUI.checkElementExist(totalTasksNotFinished), "The section Tasks Not Finished not display.");
        softAssert.assertEquals(DriverManager.getDriver().findElement(totalTasksNotFinished).getText(), value, "FAIL!! Tasks Not Finished total not match.");

        softAssert.assertAll();
    }
}
