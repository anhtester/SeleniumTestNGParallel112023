package com.anhtester.PageObjectModel.pages;

import com.anhtester.keywords.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class DashboardPage extends CommonPage {
    private WebDriver driver;

    public DashboardPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    private By buttonDashboardOptions = By.xpath("//div[normalize-space()='Dashboard Options']");
    private By totalInvoicesAwaitingPayment = By.xpath("(//span[normalize-space()='Invoices Awaiting Payment']/parent::div)/following-sibling::span");
    private By totalConvertedLeads = By.xpath("(//span[normalize-space()='Converted Leads']/parent::div)/following-sibling::span");
    private By totalProjectsInProgress = By.xpath("(//span[normalize-space()='Projects In Progress']/parent::div)/following-sibling::span");
    private By totalTasksNotFinished = By.xpath("(//span[normalize-space()='Tasks Not Finished']/parent::div)/following-sibling::span");
    private By checkboxQuickStatistics = By.xpath("//input[@id='widget_option_top_stats']");
    private By sectionQuickStatistics = By.xpath("//div[@id='widget-top_stats']");

    public void clickButtonDashboardOptions(){
        WebUI.waitForPageLoaded(driver);
        System.out.println(WebUI.checkElementExist(driver, buttonDashboardOptions));
        driver.findElement(buttonDashboardOptions).click();
    }

    public void verifyCheckboxQuickStatistics(){
        WebUI.sleep(1);
        Assert.assertTrue(driver.findElement(checkboxQuickStatistics).isSelected(), "FAIL!! The value of checkbox Quick Statistics not match.");
        Assert.assertTrue(driver.findElement(sectionQuickStatistics).isDisplayed(), "FAI!! The section Quick Statistics not display.");
    }

    public void checkTotalInvoicesAwaitingPayment(String value) {
        WebUI.waitForPageLoaded(driver);
        Assert.assertTrue(WebUI.checkElementExist(driver, totalInvoicesAwaitingPayment), "The section Invoices Awaiting Payment not display.");
        Assert.assertEquals(driver.findElement(totalInvoicesAwaitingPayment).getText(), value, "FAIL!! Invoices Awaiting Payment total not match.");
    }

    public void checkTotalConvertedLeads() {
        WebUI.waitForPageLoaded(driver);
        Assert.assertTrue(WebUI.checkElementExist(driver, totalConvertedLeads), "The section Converted Leads not display.");
        Assert.assertEquals(driver.findElement(totalConvertedLeads).getText(), "1 / 5", "FAIL!! Converted Leads total not match.");
    }

    public void checkTotalProjectsInProgress() {
        WebUI.waitForPageLoaded(driver);
        Assert.assertTrue(WebUI.checkElementExist(driver, totalProjectsInProgress), "The section Projects In Progress not display.");
        Assert.assertEquals(driver.findElement(totalProjectsInProgress).getText(), "4 / 4", "FAIL!! Projects In Progress total not match.");
    }

    public void checkTotalTasksNotFinished() {
        WebUI.waitForPageLoaded(driver);
        Assert.assertTrue(WebUI.checkElementExist(driver, totalTasksNotFinished), "The section Tasks Not Finished not display.");
        Assert.assertEquals(driver.findElement(totalTasksNotFinished).getText(), "8 / 8", "FAIL!! Tasks Not Finished total not match.");
    }
}
