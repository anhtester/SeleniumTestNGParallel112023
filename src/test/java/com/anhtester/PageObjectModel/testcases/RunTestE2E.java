package com.anhtester.PageObjectModel.testcases;

import com.anhtester.PageObjectModel.pages.DashboardPage;
import com.anhtester.PageObjectModel.pages.LoginPage;
import com.anhtester.common.BaseTestE2E;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RunTestE2E extends BaseTestE2E {

    @Test(priority = 1)
    public void testLoginSuccess(){
        getLoginPage().loginCRM("admin@example.com", "123456");
        getLoginPage().verifyLoginSuccess();
    }

    @Test(priority = 2)
    public void testCheckSectionQuickStatisticsDisplay(){
        //dashboardPage = new DashboardPage(driver);
        getDashboardPage().clickButtonDashboardOptions();
        getDashboardPage().verifyCheckboxQuickStatistics();
    }

    @Test(priority = 3)
    public void testAddNewCustomer() {

        String CUSTOMER_NAME = "AN_Customer_02";

        getDashboardPage().clickMenuCustomer(); //Hàm này nằm bên CommonPage

        int totalCustomersBefore = Integer.parseInt(getCustomerPage().getTotalCustomers());
        System.out.println("\uD83C\uDF40 Total Customers Before: " + totalCustomersBefore);
        getCustomerPage().clickAddNewButton();
        getCustomerPage().enterDataAddNewCustomer(CUSTOMER_NAME);
        getCustomerPage().checkCustomerInTableList(CUSTOMER_NAME);
        System.out.println("\uD83C\uDF40 Total Customers After: " + getCustomerPage().getTotalCustomers());
        Assert.assertEquals(getCustomerPage().getTotalCustomers(), String.valueOf(totalCustomersBefore + 1), "FAIL!! The Total Customers in Customer Page not match.");
        getCustomerPage().checkCustomerDetail(CUSTOMER_NAME);
        getCustomerPage().clickMenuProjects();
        getProjectPage().clickAddNewProject();
        getProjectPage().checkCustomerDisplayInSelectSection(CUSTOMER_NAME);
    }

}
