package com.anhtester.keywords;

import com.anhtester.constants.ConfigData;
import com.anhtester.drivers.DriverManager;
import com.anhtester.helpers.CaptureHelper;
import com.anhtester.helpers.PropertiesHelper;
import com.anhtester.helpers.SystemHelper;
import com.anhtester.reports.ExtentTestManager;
import com.anhtester.utils.LogUtils;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.List;

public class WebUI {

    private static int EXPLICIT_WAIT_TIMEOUT = ConfigData.EXPLICIT_WAIT_TIMEOUT;
    private static double STEP_TIME = ConfigData.STEP_TIME;
    private static int PAGE_LOAD_TIMEOUT = ConfigData.PAGE_LOAD_TIMEOUT;

    public static void sleep(double second) {
        try {
            Thread.sleep((long) (1000 * second));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void logConsole(Object message) {
        LogUtils.info(message);
    }

    public static WebElement getWebElement(By by) {
        return DriverManager.getDriver().findElement(by);
    }

    public static List<WebElement> getListWebElement(By by) {
        return DriverManager.getDriver().findElements(by);
    }

    public static Boolean checkElementExist(By by) {
        sleep(2);
        List<WebElement> listElement = getListWebElement(by);

        if (listElement.size() > 0) {
            LogUtils.info("✅ Check Element Exist: " + true + " ---> " + by);
            return true;
        } else {
            LogUtils.error("❌ Check Element Exist: " + false + " ---> " + by);
            return false;
        }
    }

    public static void openURL(String url) {
        DriverManager.getDriver().get(url);
        sleep(STEP_TIME);
        LogUtils.info("\uD83C\uDF10 Open URL: " + url);
        ExtentTestManager.logMessage(Status.PASS, "\uD83C\uDF10 Open URL: " + url);
        if (PropertiesHelper.getValue("SCREENSHOT_STEP_ALL").equals("true")) {
            CaptureHelper.screenshot(SystemHelper.makeSlug("openURL_" + url));
        }
    }

    public static void clickElement(By by) {
        waitForElementClickable(by);
        sleep(STEP_TIME);
        getWebElement(by).click();
        LogUtils.info("\uD83D\uDFE2 Click element: " + by);
        ExtentTestManager.logMessage(Status.PASS, "\uD83D\uDFE2 Click element: " + by);
        if (PropertiesHelper.getValue("SCREENSHOT_STEP_ALL").equals("true")) {
            CaptureHelper.screenshot(SystemHelper.makeSlug("clickElement_" + by.toString()));
        }
    }

    public static void clickElement(By by, long timeout) {
        waitForElementClickable(by);
        sleep(STEP_TIME);
        getWebElement(by).click();
        LogUtils.info("\uD83D\uDFE2 Click element: " + by);
        ExtentTestManager.logMessage(Status.PASS, "\uD83D\uDFE2 Click element: " + by);
        if (PropertiesHelper.getValue("SCREENSHOT_STEP_ALL").equals("true")) {
            CaptureHelper.screenshot(SystemHelper.makeSlug("clickElement_" + by.toString()));
        }
    }

    public static void setText(By by, String value) {
        waitForElementPresent(by);
        sleep(STEP_TIME);
        getWebElement(by).sendKeys(value);
        LogUtils.info("\uD83D\uDFE2 Set text: " + value + " on element " + by);
        ExtentTestManager.logMessage(Status.PASS, "\uD83D\uDFE2 Set text: " + value + " on element " + by);
        if (PropertiesHelper.getValue("SCREENSHOT_STEP_ALL").equals("true")) {
            CaptureHelper.screenshot(SystemHelper.makeSlug("setText_" + by.toString()));
        }
    }

    public static void setKey(By by, Keys keys) {
        getWebElement(by).sendKeys(keys);
        LogUtils.info("\uD83D\uDFE2 Set key: " + keys.name() + " on element " + by);
        ExtentTestManager.logMessage(Status.PASS, "\uD83D\uDFE2 Set key: " + keys.name() + " on element " + by);
        if (PropertiesHelper.getValue("SCREENSHOT_STEP_ALL").equals("true")) {
            CaptureHelper.screenshot(SystemHelper.makeSlug("setKey_" + by.toString()));
        }
    }

    public static String getElementText(By by) {
        waitForElementVisible(by);
        sleep(STEP_TIME);
        String text = getWebElement(by).getText();
        LogUtils.info("➡\uFE0F Get text: " + text);
        ExtentTestManager.logMessage(Status.PASS, "➡\uFE0F Get text: " + text);
        return text;
    }

    public static String getElementAttribute(By by, String attributeName) {
        waitForElementVisible(by);
        sleep(STEP_TIME);
        String text = getWebElement(by).getAttribute(attributeName);
        LogUtils.info("➡\uFE0F Attribute value: " + text);
        return text;
    }

    public static void setTextAndKey(By by, String value, Keys key) {
        waitForPageLoaded();
        getWebElement(by).sendKeys(value, key);
        LogUtils.info("Set text: " + value + " on element " + by);
    }

//    public static void scrollToElement(By element) {
//        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
//        js.executeScript("arguments[0].scrollIntoView(true);", getWebElement(element));
//    }

    public static void scrollToElementOnTop(By element) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", getWebElement(element));
    }

    public static void scrollToElementOnBottom(By element) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].scrollIntoView(false);", getWebElement(element));
    }

    /**
     * Cuộn đến vị trí phần tử chỉ định
     *
     * @param element
     * @param position nếu giá trị 'true' thì cuộn lên trên. Nếu giá trị 'false' thì cuộn xuống dưới
     */
    public static void scrollToElement(By element, String position) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].scrollIntoView(" + position + ");", getWebElement(element));
    }

    public static void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static void scrollToPosition(int X, int Y) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("window.scrollTo(" + X + "," + Y + ");");
    }

    public static boolean moveToElement(By toElement) {
        try {
            Actions action = new Actions(DriverManager.getDriver());
            action.moveToElement(getWebElement(toElement)).perform();
            return true;
        } catch (Exception e) {
            LogUtils.error(e.getMessage());
            return false;
        }
    }

    public static boolean moveToOffset(int X, int Y) {
        try {
            Actions action = new Actions(DriverManager.getDriver());
            action.moveByOffset(X, Y).build().perform();
            return true;
        } catch (Exception e) {
            LogUtils.error(e.getMessage());
            return false;
        }
    }

    public static boolean hoverElement(By by) {
        try {
            Actions action = new Actions(DriverManager.getDriver());
            action.moveToElement(getWebElement(by)).perform();
            return true;
        } catch (Exception e) {
            LogUtils.error(e.getMessage());
            return false;
        }
    }

    public static boolean mouseHover(By by) {
        try {
            Actions action = new Actions(DriverManager.getDriver());
            action.moveToElement(getWebElement(by)).perform();
            return true;
        } catch (Exception e) {
            LogUtils.error(e.getMessage());
            return false;
        }
    }

    public static boolean dragAndDrop(By fromElement, By toElement) {
        try {
            Actions action = new Actions(DriverManager.getDriver());
            action.dragAndDrop(getWebElement(fromElement), getWebElement(toElement)).perform();
            //action.clickAndHold(getWebElement(fromElement)).moveToElement(getWebElement(toElement)).release(getWebElement(toElement)).build().perform();
            return true;
        } catch (Exception e) {
            LogUtils.error(e.getMessage());
            return false;
        }
    }

    public static boolean dragAndDropElement(By fromElement, By toElement) {
        try {
            Actions action = new Actions(DriverManager.getDriver());
            action.clickAndHold(getWebElement(fromElement)).moveToElement(getWebElement(toElement)).release(getWebElement(toElement)).build().perform();
            return true;
        } catch (Exception e) {
            LogUtils.error(e.getMessage());
            return false;
        }
    }

    public static boolean dragAndDropOffset(By fromElement, int X, int Y) {
        try {
            Actions action = new Actions(DriverManager.getDriver());
            //Tính từ vị trí click chuột đầu tiên (clickAndHold)
            action.clickAndHold(getWebElement(fromElement)).pause(1).moveByOffset(X, Y).release().build().perform();
            return true;
        } catch (Exception e) {
            LogUtils.error(e.getMessage());
            return false;
        }
    }

    public static boolean pressENTER() {
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            return true;
        } catch (Exception e) {
            LogUtils.error(e.getMessage());
            return false;
        }
    }

    public static boolean pressESC() {
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ESCAPE);
            robot.keyRelease(KeyEvent.VK_ESCAPE);
            return true;
        } catch (Exception e) {
            LogUtils.error(e.getMessage());
            return false;
        }
    }

    public static boolean pressF11() {
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_F11);
            robot.keyRelease(KeyEvent.VK_F11);
            return true;
        } catch (Exception e) {
            LogUtils.error(e.getMessage());
            return false;
        }
    }

    /**
     * @param by truyền vào đối tượng element dạng By
     * @return Tô màu viền đỏ cho Element trên website
     */
    public static WebElement highLightElement(By by) {
        // Tô màu border ngoài chính element chỉ định - màu đỏ (có thể đổi màu khác)
        if (DriverManager.getDriver() instanceof JavascriptExecutor) {
            ((JavascriptExecutor) DriverManager.getDriver()).executeScript("arguments[0].style.border='2px solid red'", getWebElement(by));
            sleep(STEP_TIME);
        }
        return getWebElement(by);
    }

    public static WebElement highLightElement(By by, String colorName) {
        // Tô màu border ngoài chính element chỉ định - màu đỏ (có thể đổi màu khác)
        if (DriverManager.getDriver() instanceof JavascriptExecutor) {
            ((JavascriptExecutor) DriverManager.getDriver()).executeScript("arguments[0].style.border='2px solid " + colorName + "'", getWebElement(by));
            sleep(STEP_TIME);
        }
        return getWebElement(by);
    }

    //Assert and Verify

    public static boolean verifyEquals(Object actual, Object expected) {
        waitForPageLoaded();
        LogUtils.info("⭐\uFE0F Verify equals: " + actual + " \uD83D\uDFF0 " + expected);
        ExtentTestManager.logMessage(Status.PASS, "⭐\uFE0F Verify equals: " + actual + " \uD83D\uDFF0 " + expected);
        boolean check = actual.equals(expected);
        return check;
    }

    public static void assertEquals(Object actual, Object expected, String message) {
        waitForPageLoaded();
        LogUtils.info("⭐\uFE0F Assert equals: " + actual + " \uD83D\uDFF0 " + expected);
        ExtentTestManager.logMessage(Status.PASS, "⭐\uFE0F Assert equals: " + actual + " \uD83D\uDFF0 " + expected);
        Assert.assertEquals(actual, expected, message);
    }

    public static boolean verifyContains(String actual, String expected) {
        waitForPageLoaded();
        LogUtils.info("⭐\uFE0F Verify contains: " + actual + " and " + expected);
        ExtentTestManager.logMessage(Status.PASS, "⭐\uFE0F Verify contains: " + actual + " and " + expected);
        boolean check = actual.contains(expected);
        return check;
    }

    public static void assertContains(String actual, String expected, String message) {
        waitForPageLoaded();
        LogUtils.info("⭐\uFE0F Assert contains: " + actual + " and " + expected);
        ExtentTestManager.logMessage(Status.PASS, "⭐\uFE0F Assert contains: " + actual + " and " + expected);
        boolean check = actual.contains(expected);
        Assert.assertTrue(check, message);
    }


    //Wait for Element

    public static void waitForElementVisible(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(ConfigData.EXPLICIT_WAIT_TIMEOUT), Duration.ofMillis(500));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Throwable error) {
            LogUtils.error("Timeout waiting for the element Visible. " + by.toString());
            Assert.fail("Timeout waiting for the element Visible. " + by.toString());

        }
    }

    public static void waitForElementVisible(By by, int timeOut) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(EXPLICIT_WAIT_TIMEOUT), Duration.ofMillis(500));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Throwable error) {
            LogUtils.info("Timeout waiting for the element Visible. " + by.toString());
            Assert.fail("Timeout waiting for the element Visible. " + by.toString());
        }
    }

    public static void waitForElementPresent(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(EXPLICIT_WAIT_TIMEOUT), Duration.ofMillis(500));
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (Throwable error) {
            LogUtils.info("Element not exist. " + by.toString());
            Assert.fail("Element not exist. " + by.toString());
        }
    }

    public static void waitForElementPresent(By by, int timeOut) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(EXPLICIT_WAIT_TIMEOUT), Duration.ofMillis(500));
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (Throwable error) {
            LogUtils.info("Element not exist. " + by.toString());
            Assert.fail("Element not exist. " + by.toString());
        }
    }

    public static void waitForElementClickable(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(EXPLICIT_WAIT_TIMEOUT), Duration.ofMillis(500));
            wait.until(ExpectedConditions.elementToBeClickable(getWebElement(by)));
        } catch (Throwable error) {
            LogUtils.info("Timeout waiting for the element ready to click. " + by.toString());
            Assert.fail("Timeout waiting for the element ready to click. " + by.toString());
        }
    }

    public static void waitForElementClickable(By by, int timeOut) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(EXPLICIT_WAIT_TIMEOUT), Duration.ofMillis(500));
            wait.until(ExpectedConditions.elementToBeClickable(getWebElement(by)));
        } catch (Throwable error) {
            LogUtils.info("Timeout waiting for the element ready to click. " + by.toString());
            Assert.fail("Timeout waiting for the element ready to click. " + by.toString());
        }
    }


    public static void waitForAlertIsPresent() {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent());
    }

    public static void waitForElementVisible(WebDriver driver, By by) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public static void waitForElementToBeClickable(By by) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public static Boolean checkElementExist(WebDriver driver, By by) {
        List<WebElement> listElement = DriverManager.getDriver().findElements(by);

        if (listElement.size() > 0) {
            LogUtils.info("Element " + by + " existing.");
            ExtentTestManager.logMessage(Status.INFO, "Element " + by + " existing.");
            return true;
        } else {
            LogUtils.info("Element " + by + " NOT exist.");
            ExtentTestManager.logMessage(Status.INFO, "Element " + by + " NOT exist.");
            return false;
        }
    }

    /**
     * Wait for Page loaded
     * Chờ đợi trang tải xong (Javascript tải xong)
     */
    public static void waitForPageLoaded(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(PAGE_LOAD_TIMEOUT), Duration.ofMillis(500));
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();

        //Wait for Javascript to load
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return js.executeScript("return document.readyState").toString().equals("complete");
            }
        };

        //Check JS is Ready
        boolean jsReady = js.executeScript("return document.readyState").toString().equals("complete");

        //Wait Javascript until it is Ready!
        if (!jsReady) {
            LogUtils.info("Javascript is NOT Ready.");
            //Wait for Javascript to load
            try {
                wait.until(jsLoad);
            } catch (Throwable error) {
                LogUtils.error("FAILED. Timeout waiting for page load.");
                LogUtils.error(error.getMessage());
                Assert.fail("FAILED. Timeout waiting for page load.");
            }
        }
    }

    public static void waitForPageLoaded() {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(PAGE_LOAD_TIMEOUT), Duration.ofMillis(500));
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();

        //Wait for Javascript to load
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return js.executeScript("return document.readyState").toString().equals("complete");
            }
        };

        //Check JS is Ready
        boolean jsReady = js.executeScript("return document.readyState").toString().equals("complete");

        //Wait Javascript until it is Ready!
        if (!jsReady) {
            LogUtils.info("Javascript is NOT Ready.");
            //Wait for Javascript to load
            try {
                wait.until(jsLoad);
            } catch (Throwable error) {
                LogUtils.error("FAILED. Timeout waiting for page load.");
                Assert.fail("FAILED. Timeout waiting for page load.");
            }
        }
    }

    public static void waitForPageLoaded(int timeout) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeout), Duration.ofMillis(500));
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();

        //Wait for Javascript to load
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return js.executeScript("return document.readyState").toString().equals("complete");
            }
        };

        //Check JS is Ready
        boolean jsReady = js.executeScript("return document.readyState").toString().equals("complete");

        //Wait Javascript until it is Ready!
        if (!jsReady) {
            LogUtils.info("Javascript is NOT Ready.");
            //Wait for Javascript to load
            try {
                wait.until(jsLoad);
            } catch (Throwable error) {
                LogUtils.error("FAILED. Timeout waiting for page load.");
                Assert.fail("FAILED. Timeout waiting for page load.");
            }
        }
    }

}
