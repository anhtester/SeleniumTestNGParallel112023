package com.anhtester.PageObjectModel.testcases;

import com.anhtester.PageObjectModel.pages.DashboardPage;
import com.anhtester.PageObjectModel.pages.LoginPage;
import com.anhtester.common.BaseTest;
import org.testng.annotations.Test;

public class DashboardTest extends BaseTest {

    LoginPage loginPage;
    DashboardPage dashboardPage;

    @Test(priority = 1)
    public void testCheckSectionQuickStatisticsDisplay(){
        loginPage = new LoginPage(driver);
        dashboardPage = loginPage.loginCRM("admin@example.com", "123456");

        //dashboardPage = new DashboardPage(driver);
        dashboardPage.clickButtonDashboardOptions();
        dashboardPage.verifyCheckboxQuickStatistics();
    }

    @Test(priority = 2)
    public void testCheckTotalSectionQuickStatistics(){
        loginPage = new LoginPage(driver);
        dashboardPage = loginPage.loginCRM("admin@example.com", "123456");

        //dashboardPage = new DashboardPage(driver);
        dashboardPage.checkTotalInvoicesAwaitingPayment("3 / 3");
        dashboardPage.checkTotalConvertedLeads();
        dashboardPage.checkTotalProjectsInProgress();
        dashboardPage.checkTotalTasksNotFinished();
    }
}
