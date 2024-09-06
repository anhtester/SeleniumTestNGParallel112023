package com.anhtester.handle_UploadFile;

import com.anhtester.common.BaseTest;
import com.anhtester.drivers.DriverManager;
import com.anhtester.helpers.SystemHelper;
import com.anhtester.keywords.WebUI;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

public class HandleUploadFile extends BaseTest {
    @Test
    public void testUploadFileWithSendKeys() {
        WebUI.openURL("https://cgi-lib.berkeley.edu/ex/fup.html");

        WebUI.waitForPageLoaded();
        WebUI.sleep(2);

        By inputFileUpload = By.xpath("//input[@name='upfile']");

        String filePath = SystemHelper.getCurrentDir() + "src\\test\\resources\\testdata\\image2.jpg";
        WebUI.setText(inputFileUpload, filePath);

        WebUI.sleep(2);
        WebUI.clickElement(By.xpath("//input[@value='Press']"));
        WebUI.waitForPageLoaded();
        Assert.assertTrue(WebUI.checkElementExist(By.xpath("//h1[normalize-space()='File Upload Results']")), "Can not upload file.");
    }

    @Test
    public void testUploadFileWithRobotClass() {
        WebUI.openURL("https://files.fm/");
        WebUI.waitForPageLoaded();
        WebUI.sleep(2);

        By textOnPage = By.xpath("//div[@id='file_select_dragndrop_text']");
        By divFileUpload = By.xpath("//div[@id='uploadifive-file_upload']");
        By inputFileUpload = By.xpath("//div[@id='file_select_button']//input[@id='file_upload']");

        String filePath = SystemHelper.getCurrentDir() + "src\\test\\resources\\testdata\\image1.png";

        WebUI.uploadFileWithRobotClass(divFileUpload, filePath);

        //Verify file đã upload thành công
        By fileNameAfterUploadSuccess = By.xpath("//span[@class='filename']");

        Assert.assertTrue(WebUI.checkElementExist(fileNameAfterUploadSuccess), "Can not upload file.");
        WebUI.assertEquals(WebUI.getElementText(fileNameAfterUploadSuccess), "image1.png", "The file name not match.");
    }
}
