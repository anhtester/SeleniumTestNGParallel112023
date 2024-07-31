package com.anhtester.Bai29_DataProvider.pages;

import com.anhtester.drivers.DriverManager;
import com.anhtester.keywords.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;

import java.util.Hashtable;

import static com.anhtester.keywords.WebUI.*;

public class CustomerPage extends CommonPage {

    public CustomerPage() {
    }

    //Elements
    private By buttonAddNewCustomer = By.xpath("//a[normalize-space()='New Customer']");
    private By headerPage = By.xpath("//span[normalize-space()='Customers Summary']");
    private By inputSearchCustomer = By.xpath("//div[@id='clients_filter']//input[@placeholder='Search...']");
    private By firstItemCustomerName = By.xpath("//tbody/tr[1]/td[3]/a");
    private By inputCompany = By.xpath("//input[@id='company']");
    private By inputVat = By.xpath("//input[@id='vat']");
    private By inputPhone = By.xpath("//input[@id='phonenumber']");
    private By inputWebsite = By.xpath("//input[@id='website']");
    private By selectGroups = By.xpath("//button[@data-id='groups_in[]']");
    private By inputGroups = By.xpath("//button[@data-id='groups_in[]']/following-sibling::div//input");
    private By selectLanguage = By.xpath("//button[@data-id='default_language']");
    private By itemVietnam = By.xpath("//span[normalize-space()='Vietnamese']");
    private By inputAddress = By.xpath("//textarea[@id='address']");
    private By inputCity = By.xpath("//input[@id='city']");
    private By inputState = By.xpath("//input[@id='state']");
    private By inputZip = By.xpath("//input[@id='zip']");
    private By selectCountry = By.xpath("//button[@data-id='country']");
    private By inputCountry = By.xpath("//button[@data-id='country']/following-sibling::div//input");
    private By buttonSave = By.xpath("//div[@id='profile-save-section']//button[normalize-space()='Save']");
    private By alertMessage = By.xpath("//span[@class='alert-title']");
    private By totalCustomers = By.xpath("//span[text()='Total Customers']/preceding-sibling::span");

    //Hàm xử lý cho trang Customer
    public void clickAddNewButton() {
        clickElement(buttonAddNewCustomer);
    }

    public String getTotalCustomers() {
        waitForPageLoaded();
        return WebUI.getElementText(totalCustomers);
    }

    public void selectLanguage(String languageName) {
        clickElement(selectLanguage);
        clickElement(By.xpath("//span[normalize-space()='" + languageName + "']"));
    }

    public void enterDataAddNewCustomer(Hashtable<String, String> data) {
        setText(inputCompany, data.get("CUSTOMER_NAME"));
        setText(inputVat, data.get("VAT"));
        setText(inputPhone, data.get("PHONE"));
        setText(inputWebsite, data.get("WEBSITE"));
        clickElement(selectGroups);
        sleep(1);
        setText(inputGroups, data.get("GROUP"));
        sleep(1);
        setKey(inputGroups, Keys.ENTER);
        sleep(1);
        clickElement(selectGroups);
        selectLanguage(data.get("LANGUAGE"));
        sleep(1);

        setText(inputAddress, data.get("ADDRESS"));
        setText(inputCity, data.get("CITY"));
        setText(inputState, data.get("STATE"));
        setText(inputZip, data.get("ZIP"));

        clickElement(selectCountry);
        sleep(1);
        setText(inputCountry, data.get("COUNTRY"));
        sleep(1);
        setKey(selectCountry, Keys.ENTER);
        sleep(1);
        clickElement(buttonSave);
        WebUI.waitForPageLoaded();
        Assert.assertTrue(checkElementExist(alertMessage), "\uD83D\uDC1E FAIL!! The alert message success not display.");
        Assert.assertEquals(DriverManager.getDriver().findElement(alertMessage).getText().trim(), "Customer added successfully.", "\uD83D\uDC1E FAIL!! The content of alert message not match.");
    }

    public void checkCustomerInTableList(Hashtable<String, String> data) {
        waitForPageLoaded();
        clickElement(menuCustomers);
        waitForPageLoaded();
        setText(inputSearchCustomer, data.get("CUSTOMER_NAME"));
        waitForPageLoaded();
        sleep(2);

        //Check customer name display in table
        Assert.assertTrue(checkElementExist(firstItemCustomerName), "\uD83D\uDC1E FAIL!! The customer name not display in table.");
        //Assert.assertEquals(driver.findElement(firstItemCustomerName).getText(), customerName, "\uD83D\uDC1E FAILL!! The customer name not match.");

        assertEquals(getElementText(firstItemCustomerName), data.get("CUSTOMER_NAME"), "\uD83D\uDC1E FAIL!! The customer name not match.");

    }

    public void checkCustomerDetail(Hashtable<String, String> data) {
        //Check cutsomer detail in Customer Detail page
        waitForPageLoaded();
        clickElement(firstItemCustomerName);
        waitForPageLoaded();
        assertEquals(getElementAttribute(inputCompany, "value"), data.get("CUSTOMER_NAME"), "FAIL!! The Company name not match.");
        assertEquals(getElementAttribute(inputVat, "value"), data.get("VAT"), "FAIL!! The VAT value not match.");
        assertEquals(getElementAttribute(inputPhone, "value"), data.get("PHONE"), "FAIL!! The Phone value not match.");
        assertEquals(getElementAttribute(inputWebsite, "value"), data.get("WEBSITE"), "FAIL!! The Website value not match.");
        assertEquals(getElementAttribute(selectGroups, "title"), data.get("GROUP"), "FAIL!! The Group of customer not match.");
        assertEquals(getElementAttribute(selectLanguage, "title"), data.get("LANGUAGE"), "FAIL!! The Language value not match.");
        assertEquals(getElementAttribute(inputAddress, "value"), data.get("ADDRESS"), "FAIL!! The Address value not match.");
    }
}
