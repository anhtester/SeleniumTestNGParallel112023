package com.anhtester.Bai28_ExcelFileData.pages;

import com.anhtester.keywords.WebUI;
import org.openqa.selenium.By;

public class CommonPage {

    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private CustomerPage customerPage;
    private ProjectPage projectPage;

    public CommonPage() {

    }

    public By menuDashboard = By.xpath("//span[normalize-space()='Dashboard']");
    public By menuCustomers = By.xpath("//span[normalize-space()='Customers']");
    public By menuSales = By.xpath("//li[@class='menu-item-sales']");
    public By menuProjects = By.xpath("//span[normalize-space()='Projects']");
    public By itemNotifications = By.xpath("//a[contains(@class,'notifications-icon')]");

    public LoginPage getLoginPage() {
        if(loginPage == null){
            loginPage = new LoginPage();
        }
        return loginPage;
    }

    public DashboardPage getDashboardPage() {
        if(dashboardPage == null){
            dashboardPage = new DashboardPage();
        }
        return dashboardPage;
    }

    public CustomerPage getCustomerPage() {
        if(customerPage == null){
            customerPage = new CustomerPage();
        }
        return customerPage;
    }

    public ProjectPage getProjectPage() {
        if(projectPage == null){
            projectPage = new ProjectPage();
        }
        return projectPage;
    }

    public DashboardPage clickMenuDashboard() {
        WebUI.waitForPageLoaded();
        WebUI.clickElement(menuDashboard);

        return new DashboardPage();
    }

    public CustomerPage clickMenuCustomer() {
        WebUI.waitForPageLoaded();
        WebUI.clickElement(menuCustomers);

        return new CustomerPage();
    }

    public ProjectPage clickMenuProjects() {
        WebUI.waitForPageLoaded();
        WebUI.clickElement(menuProjects);

        return new ProjectPage();
    }

}
