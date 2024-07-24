package com.anhtester.Bai28_ExcelFileData.testcases;

import com.anhtester.Bai28_ExcelFileData.pages.CustomerPage;
import com.anhtester.Bai28_ExcelFileData.pages.DashboardPage;
import com.anhtester.Bai28_ExcelFileData.pages.LoginPage;
import com.anhtester.Bai28_ExcelFileData.pages.ProjectPage;
import com.anhtester.common.BaseTest;
import com.anhtester.helpers.ExcelHelper;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CustomerTest extends BaseTest {

    LoginPage loginPage;
    DashboardPage dashboardPage;
    CustomerPage customerPage;
    ProjectPage projectPage;

    @Test
    public void testAddNewCustomer() {

        String CUSTOMER_NAME = "AN_Customer_01";
        ExcelHelper excelHelper = new ExcelHelper();
        excelHelper.setExcelFile("src/test/resources/testdata/Login.xlsx", "Customer");


        loginPage = new LoginPage();
        dashboardPage = loginPage.loginCRM("admin@example.com", "123456");

        customerPage = dashboardPage.clickMenuCustomer(); //Hàm này nằm bên CommonPage

        int totalCustomersBefore = Integer.parseInt(customerPage.getTotalCustomers());
        System.out.println("\uD83C\uDF40 Total Customers Before: " + totalCustomersBefore);
        customerPage.clickAddNewButton();
        customerPage.enterDataAddNewCustomer(CUSTOMER_NAME, 2);
        customerPage.checkCustomerInTableList(excelHelper.getCellData("CUSTOMER_NAME", 2));
        System.out.println("\uD83C\uDF40 Total Customers After: " + customerPage.getTotalCustomers());
        Assert.assertEquals(customerPage.getTotalCustomers(), String.valueOf(totalCustomersBefore + 1), "FAIL!! The Total Customers in Customer Page not match.");
        customerPage.checkCustomerDetail(excelHelper.getCellData("CUSTOMER_NAME", 2));
        projectPage = customerPage.clickMenuProjects();
        projectPage.clickAddNewProject();
        projectPage.checkCustomerDisplayInSelectSection(excelHelper.getCellData("CUSTOMER_NAME", 2));
    }
}
