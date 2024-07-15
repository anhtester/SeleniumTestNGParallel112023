package com.anhtester.Bai27_PropertiesConfig.pages;

import com.anhtester.drivers.DriverManager;
import com.anhtester.keywords.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;

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

    public void enterDataAddNewCustomer(String customerName) {
        setText(inputCompany, customerName);
        setText(inputVat, "10");
        setText(inputPhone, "123456");
        setText(inputWebsite, "https://anhtester.com");
        clickElement(selectGroups);
        sleep(1);
        setText(inputGroups, "VIP");
        sleep(1);
        setKey(inputGroups, Keys.ENTER);
        sleep(1);
        clickElement(selectGroups);
        selectLanguage("Vietnamese");
        sleep(1);

        setText(inputAddress, "Can Tho");
        setText(inputCity, "Can Tho");
        setText(inputState, "Can Tho");
        setText(inputZip, "12345");

        clickElement(selectCountry);
        sleep(1);
        setText(inputCountry, "Vietnam");
        sleep(1);
        setKey(selectCountry, Keys.ENTER);
        sleep(1);
        clickElement(buttonSave);
        WebUI.waitForPageLoaded();
        Assert.assertTrue(checkElementExist(alertMessage), "\uD83D\uDC1E FAIL!! The alert message success not display.");
        Assert.assertEquals(DriverManager.getDriver().findElement(alertMessage).getText().trim(), "Customer added successfully.", "\uD83D\uDC1E FAIL!! The content of alert message not match.");
    }

    public void checkCustomerInTableList(String customerName) {
        waitForPageLoaded();
        clickElement(menuCustomers);
        waitForPageLoaded();
        setText(inputSearchCustomer, customerName);
        waitForPageLoaded();
        sleep(2);

        //Check customer name display in table
        Assert.assertTrue(checkElementExist(firstItemCustomerName), "\uD83D\uDC1E FAIL!! The customer name not display in table.");
        //Assert.assertEquals(driver.findElement(firstItemCustomerName).getText(), customerName, "\uD83D\uDC1E FAILL!! The customer name not match.");

        assertEquals(getElementText(firstItemCustomerName), customerName, "\uD83D\uDC1E FAIL!! The customer name not match.");

    }

    public void checkCustomerDetail(String customerName) {
        //Check cutsomer detail in Customer Detail page
        waitForPageLoaded();
        clickElement(firstItemCustomerName);
        waitForPageLoaded();
        assertEquals(getElementAttribute(inputCompany, "value"), customerName, "FAIL!! The Company name not match.");
        assertEquals(getElementAttribute(inputVat, "value"), "10", "FAIL!! The VAT value not match.");
        assertEquals(getElementAttribute(inputPhone, "value"), "123456", "FAIL!! The Phone value not match.");
        assertEquals(getElementAttribute(inputWebsite, "value"), "https://anhtester.com", "FAIL!! The Website value not match.");
        assertEquals(getElementAttribute(selectGroups, "title"), "VIP", "FAIL!! The Group of customer not match.");
        assertEquals(getElementAttribute(selectLanguage, "title"), "Vietnamese", "FAIL!! The Language value not match.");
        assertEquals(getElementAttribute(inputAddress, "value"), "Can Tho", "FAIL!! The Address value not match.");
    }
}
