package com.anhtester.Bai30_Screenshot_RecordVideo;

import com.anhtester.common.BaseTest;
import com.anhtester.drivers.DriverManager;
import com.anhtester.helpers.CaptureHelper;
import com.anhtester.keywords.WebUI;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DemoRecordVideo extends BaseTest {
    @Test
    public void testBlogPage() {
        CaptureHelper.startRecord("testBlogPage");

        WebUI.openURL("https://anhtester.com");
        Assert.assertEquals(DriverManager.getDriver().getTitle(), "Anh Tester Automation Testing");

        WebUI.clickElement(By.xpath("//a[normalize-space()='blog']"));
        WebUI.waitForPageLoaded();
        WebUI.clickElement(By.xpath("//a[normalize-space()='Selenium']"));
        WebUI.waitForPageLoaded();
        WebUI.clickElement(By.xpath("(//div[@class='card-image'])[1]"));
        WebUI.waitForPageLoaded();

        CaptureHelper.stopRecord();
    }
}
