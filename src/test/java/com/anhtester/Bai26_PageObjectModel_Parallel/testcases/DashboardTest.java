package com.anhtester.Bai26_PageObjectModel_Parallel.testcases;

import com.anhtester.Bai26_PageObjectModel_Parallel.pages.DashboardPage;
import com.anhtester.Bai26_PageObjectModel_Parallel.pages.LoginPage;
import com.anhtester.common.BaseTest;
import org.testng.annotations.Test;

public class DashboardTest extends BaseTest {

    LoginPage loginPage;
    DashboardPage dashboardPage;

    @Test(priority = 1)
    public void testCheckSectionQuickStatisticsDisplay(){
        loginPage = new LoginPage();
        dashboardPage = loginPage.loginCRM("admin@example.com", "123456");

        //dashboardPage = new DashboardPage(driver);
        dashboardPage.clickButtonDashboardOptions();
        dashboardPage.verifyCheckboxQuickStatistics();
    }

    @Test(priority = 2)
    public void testCheckTotalSectionQuickStatistics(){
        loginPage = new LoginPage();
        dashboardPage = loginPage.loginCRM("admin@example.com", "123456");

        //dashboardPage = new DashboardPage(driver);
        dashboardPage.checkTotalInvoicesAwaitingPayment("5 / 5");
        dashboardPage.checkTotalConvertedLeads("6 / 12");
        dashboardPage.checkTotalProjectsInProgress("2 / 2");
        dashboardPage.checkTotalTasksNotFinished("20 / 21");
    }
}