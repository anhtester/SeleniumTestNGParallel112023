package com.anhtester.listeners;

import com.anhtester.helpers.CaptureHelper;
import com.anhtester.helpers.PropertiesHelper;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    @Override
    public void onStart(ITestContext iTestContext) {
        System.out.println("⭐\uFE0F ********* onStart *********");
        PropertiesHelper.loadAllFiles();
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        System.out.println("⭐\uFE0F ********* onFinish *********");
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        System.out.println("➡\uFE0F onTestStart");
        //CaptureHelper.startRecord(iTestResult.getName());
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println("✅ onTestSuccess");
        //CaptureHelper.stopRecord();
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        System.out.println("❌ onTestFailure");
        CaptureHelper.screenshot(iTestResult.getName());
        //CaptureHelper.stopRecord();
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        System.out.println("\uD83D\uDD1C onTestSkipped");
        //CaptureHelper.stopRecord();
    }
}