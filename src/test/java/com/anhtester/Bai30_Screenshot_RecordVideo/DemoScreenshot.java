package com.anhtester.Bai30_Screenshot_RecordVideo;

import com.anhtester.common.BaseTest;
import com.anhtester.drivers.DriverManager;
import com.anhtester.helpers.CaptureHelper;
import com.anhtester.keywords.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.IOException;

public class DemoScreenshot extends BaseTest {
    @Test
    public void testHomePage() {

        WebUI.openURL("https://anhtester.com");
        Assert.assertEquals(DriverManager.getDriver().getTitle(), "123 Anh Tester Automation Testing");

        CaptureHelper.screenshot("screenHome");

        WebUI.clickElement(By.xpath("//a[@id='btn-login']"));
        WebUI.waitForPageLoaded();

        CaptureHelper.screenshot("screenLogin");
    }

    @Test
    public void testBlogPage() {

        WebUI.openURL("https://anhtester.com");
        Assert.assertEquals(DriverManager.getDriver().getTitle(), "Anh Tester Automation Testing");

        WebUI.clickElement(By.xpath("//a[normalize-space()='blog']"));
        WebUI.waitForPageLoaded();

        CaptureHelper.screenshot("screenBlog");

    }
}
