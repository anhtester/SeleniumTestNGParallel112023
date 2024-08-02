package com.anhtester.common;

import com.anhtester.drivers.DriverManager;
import com.anhtester.helpers.CaptureHelper;
import com.anhtester.helpers.PropertiesHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

public class BaseTest {

    @BeforeSuite
    public void setupEnvironment(){
        PropertiesHelper.loadAllFiles();
    }

    @BeforeMethod
    @Parameters({"browser"})
    public void createDriver(@Optional("chrome") String browser) {
        WebDriver driver;
        if(PropertiesHelper.getValue("BROWSER") != null && !PropertiesHelper.getValue("BROWSER").isEmpty()){
            driver = setupDriver(PropertiesHelper.getValue("BROWSER"));
        }else {
            driver = setupDriver(browser);
        }
        DriverManager.setDriver(driver); //Gán giá trị driver vào trong ThreadLocal
    }

    public WebDriver setupDriver(String browserName) {
        WebDriver driver;

        switch (browserName.trim().toLowerCase()) {
            case "chrome":
                driver = initChromeDriver();
                break;
            case "firefox":
                driver = initFirefoxDriver();
                break;
            case "edge":
                driver = initEdgeDriver();
                break;
            default:
                System.out.println("Browser: " + browserName + " is invalid, Launching Chrome as browser of choice...");
                driver = initChromeDriver();
        }
        return driver;
    }

    private WebDriver initChromeDriver() {
        System.out.println("Launching Chrome browser...");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        return driver;
    }

    private WebDriver initEdgeDriver() {
        System.out.println("Launching Edge browser...");
        WebDriver driver = new EdgeDriver();
        driver.manage().window().maximize();
        return driver;
    }

    private WebDriver initFirefoxDriver() {
        System.out.println("Launching Firefox browser...");
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        return driver;
    }

    @AfterMethod
    public void closeDriver(ITestResult iTestResult){

        if(iTestResult.getStatus() == ITestResult.FAILURE){
            CaptureHelper.screenshot(iTestResult.getName());
        }

        DriverManager.quit();
    }

}