package com.anhtester.listeners;

import com.anhtester.helpers.CaptureHelper;
import com.anhtester.helpers.PropertiesHelper;
import com.anhtester.utils.LogUtils;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    @Override
    public void onStart(ITestContext iTestContext) {
        LogUtils.info("⭐\uFE0F ********* START TESTING " + iTestContext.getName() + " *********");
        PropertiesHelper.loadAllFiles();
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        LogUtils.info("⭐\uFE0F ********* END TESTING " + iTestContext.getName() + " *********");
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        LogUtils.info("➡\uFE0F Starting test case " + iTestResult.getName());

        if(PropertiesHelper.getValue("RECORD_VIDEO").equals("true")){
            CaptureHelper.startRecord(iTestResult.getName());
        }

    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        LogUtils.info("✅ Test case " + iTestResult.getName() + " passed.");

        if(PropertiesHelper.getValue("SCREENSHOT_STEP_PASS").equals("true")) {
            CaptureHelper.screenshot(iTestResult.getName());
        }

        if(PropertiesHelper.getValue("RECORD_VIDEO").equals("true")){
            CaptureHelper.stopRecord();
        }

    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        LogUtils.error("❌ Test case " + iTestResult.getName() + " failed.");
        LogUtils.error(iTestResult.getThrowable());

        if(PropertiesHelper.getValue("SCREENSHOT_STEP_FAIL").equals("true")) {
            CaptureHelper.screenshot(iTestResult.getName());
        }

        if(PropertiesHelper.getValue("RECORD_VIDEO").equals("true")){
            CaptureHelper.stopRecord();
        }
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        LogUtils.warn("\uD83D\uDD1C Test case " + iTestResult.getName() + " skipped.");
        if(PropertiesHelper.getValue("RECORD_VIDEO").equals("true")){
            CaptureHelper.stopRecord();
        }
    }
}