package Appium.generic;

import Appium.reports.ExtentLogger;
import Appium.utils.Constants;
import Appium.utils.ContextManager;
import com.google.common.collect.ImmutableList;
import io.appium.java_client.*;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;



public class CommonFunctions implements Constants {

    public AppiumDriver driver;
    WebDriverWait wait = null;
    public static Dimension windowSize;
    public static Duration SCROLL_DUR = Duration.ofMillis(1000);
    public static double SCROLL_RATIO = 0.8;
    public static int ANDROID_SCROLL_DIVISOR = 3;

    public CommonFunctions(AppiumDriver driver) {
        this.driver = driver;
    }

    public void sendKeys(WebElement element, String data, String elementName) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(element));
            element.clear();
            element.sendKeys(data);
            Thread.sleep(200);
            ExtentLogger.info(elementName + " is entered :" + data);
        } catch (Exception e) {
            System.out.println("Unable to find element: " + element);
            e.printStackTrace();
        }
    }

    /**
     * This function will wait for the element to be visible and clickable
     */

    public void waitTillTheElementIsVisibleAndClickable(WebElement element) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(element));

        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * This function will wait for the element to be visible and clickable and then clicks on it
     */

    public void waitAndClick(WebElement element, String elementName) {
        waitTillTheElementIsVisibleAndClickable(element);
        element.click();
        ExtentLogger.info(elementName + " is Clicked");


    }

    public WebElement element(WebElement element) {
        try {
            waitTillTheElementIsVisibleAndClickable(element);
        } catch (NoSuchElementException | TimeoutException e) {

        }
        return element;
    }

    /**
     * This Function is to generateXpath using text and Check element is present using assert
     *
     * @author Prashanth
     * @param: String
     */
    public boolean generateTextXpathIsElementPresent(String text) {
        boolean flag = false;
        List<WebElement> elements = driver.findElements(By.xpath("//android.widget.TextView[contains(@text,'" + text + "')]"));
        if (elements.size() > 0) {
            flag = true;
            System.out.println("Check the " + text + " element is present");
        }
        Assert.assertTrue(flag, "Element is not present");
        return true;
    }


    /**
     * This Function is to check the element is present or not
     *
     * @author Prashanth
     * @param: Mobile Element
     */
    public boolean isElementDisplayed(WebElement locator) {
        try {
            if (locator.isDisplayed())
                System.out.println("Element present on screen ***********" + locator);
            return true;
        } catch (NoSuchElementException e) {
            System.out.println("Element not present on screen **************" + locator);
            return false;
        }
    }


    /**
     * This Function will pause the execution for given secs.
     *
     * @param secs : No of seconds to be paused.
     */
    public void waitInSec(int secs) {
        try {
            Thread.sleep(secs * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * This Function is to hide keyboard
     */
//    public void hideKeyBoard() {
//        try {
//            ((AndroidDriver) driver).hideKeyboard();
//            System.out.println("Hide KeyBoard");
//        } catch (Exception e) {
//            System.out.println("KeyBoard not found to hide");
//        }
//    }

    /**
     * This function will wait for the element to be visible
     */
    public void waitForElementToAppear(WebElement id) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(id));
    }

    /**
     * This Function is to accept Alert
     */
    public void acceptAlert() {
        try {
            Thread.sleep(2000);
            driver.switchTo().alert().accept();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean isElementPresent(WebElement locator) throws NoSuchElementException {

        try {
            waitForElementToAppear(locator);
            if (locator.isDisplayed())
                System.out.println("Element present on screen ***********" + locator);
            return true;
        } catch (Exception e) {
            System.out.println("Element not present on screen **************" + locator);
            return false;
        }
    }


//    public enum DIRECTION {
//        DOWN, UP, LEFT, RIGHT;
//    }
//
//    public static void swipe(AppiumDriver driver, DIRECTION direction) {
//        Dimension size = driver.manage().window().getSize();
//
//        int startX = 0;
//        int endX = 0;
//        int startY = 0;
//        int endY = 0;
//
//        switch (direction) {
//            case RIGHT:
//                startY = (int) (size.height / 2);
//                startX = (int) (size.width * 0.90);
//                endX = (int) (size.width * 0.05);
//                new TouchAction(driver)
//                        .press(point(startX, startY))
//                        .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(3)))
//                        .moveTo(point(endX, startY))
//                        .release()
//                        .perform();
//                break;
//
//            case LEFT:
//                startY = (int) (size.height / 2);
//                startX = (int) (size.width * 0.05);
//                endX = (int) (size.width * 0.90);
//                new TouchAction(driver)
//                        .press(point(startX, startY))
//                        .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(3)))
//                        .moveTo(point(endX, startY))
//                        .release()
//                        .perform();
//
//                break;
//
//            case UP:
//                endY = (int) (size.height * 0.70);
//                startY = (int) (size.height * 0.30);
//                startX = (size.width / 2);
//                new TouchAction(driver)
//                        .press(point(startX, startY))
//                        .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(3)))
//                        .moveTo(point(startX, endY))
//                        .release()
//                        .perform();
//                break;
//
//
//            case DOWN:
//                startY = (int) (size.height * 0.70);
//                endY = (int) (size.height * 0.30);
//                startX = (size.width / 2);
//                new TouchAction(driver)
//                        .press(point(startX, startY))
//                        .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(3)))
//                        .moveTo(point(startX, endY))
//                        .release()
//                        .perform();
//
//                break;
//
//        }
//    }
//
//
//    public void swipeUp() {
//        swipe(driver, DIRECTION.UP);
//    }
//
//    public void swipeDown() {
//        swipe(driver, DIRECTION.DOWN);
//
//    }
//
//    public void swipeRight() {
//        swipe(driver, DIRECTION.RIGHT);
//
//    }
//
//    public void swipeLeft() {
//        swipe(driver, DIRECTION.LEFT);
//
//    }

   /* public static void scrollNClick(By listItems, String Text) {
        boolean flag = false;

        while (true) {
            for (WebElement el : (AppiumDriver) ContextManager.getDriver().findElement(listItems)) {
                if (el.getAttribute("text").equals(Text)) {
                    el.click();
                    flag = true;
                    break;
                }
            }
            if (flag)
                break;
            else
                scroll(ScrollDirection.DOWN, SCROLL_RATIO);
        }
    } */

    public void scrollNClick(WebElement el) {
        int retry = 0;
        while (retry <= 5) {
            try {
                el.click();
                break;
            } catch (org.openqa.selenium.NoSuchElementException e) {
                //scrollDown();
                scroll(ScrollDirection.DOWN, SCROLL_RATIO);
                retry++;
            }
        }
    }

    public void scrollIntoView(String Text) {

        String mySelector = "new UiSelector().text(\"" + Text + "\").instance(0)";
        String command = "new UiScrollable(new UiSelector().scrollable(true).instance(0))";
        (ContextManager.getDriver()).findElement(AppiumBy.androidUIAutomator(command));
    }

    public void scrollTo(String Text) {
        if (ContextManager.getDriver() instanceof AndroidDriver) {
            scrollIntoView(Text);
        }
    }

    public enum ScrollDirection {
        UP, DOWN, LEFT, RIGHT
    }

    private Dimension getWindowSize() {
        if (windowSize == null) {
            windowSize = ContextManager.getDriver().manage().window().getSize();
        }
        return windowSize;
    }

    public void scroll(ScrollDirection dir, double scrollRatio) {
        if (scrollRatio < 0 || scrollRatio > 1) {
            throw new Error("Scroll distance must be between 0 and 1");
        }
        Dimension size = getWindowSize();
        Point midPoint = new Point((int) (size.width * 0.5), (int) (size.height * 0.5));
        int top = midPoint.y - (int) ((size.height * scrollRatio) * 0.5);
        int bottom = midPoint.y + (int) ((size.height * scrollRatio) * 0.5);
        int left = midPoint.x - (int) ((size.width * scrollRatio) * 0.5);
        int right = midPoint.x + (int) ((size.width * scrollRatio) * 0.5);
        if (dir == ScrollDirection.UP) {
            swipe(new Point(midPoint.x, top), new Point(midPoint.x, bottom), SCROLL_DUR);
        } else if (dir == ScrollDirection.DOWN) {
            swipe(new Point(midPoint.x, bottom), new Point(midPoint.x, top), SCROLL_DUR);
        } else if (dir == ScrollDirection.LEFT) {
            swipe(new Point(left, midPoint.y), new Point(right, midPoint.y), SCROLL_DUR);
        } else {
            swipe(new Point(right, midPoint.y), new Point(left, midPoint.y), SCROLL_DUR);
        }
    }

    public void swipe(Point start, Point end, Duration dur) {
        boolean isAndroid = ContextManager.getDriver() instanceof AndroidDriver;

        PointerInput input = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence swipe = new Sequence(input, 0);
        swipe.addAction(input.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), start.x, start.y));
        swipe.addAction(input.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        if (isAndroid) {
            dur = dur.dividedBy(ANDROID_SCROLL_DIVISOR);
        } else {
            swipe.addAction(new Pause(input, dur));
            dur = Duration.ZERO;
        }
        swipe.addAction(input.createPointerMove(dur, PointerInput.Origin.viewport(), end.x, end.y));
        swipe.addAction(input.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        ((AppiumDriver) ContextManager.getDriver()).perform(ImmutableList.of(swipe));
    }


    public static void longPress(WebElement el) {
        Point location = el.getLocation();
        PointerInput input = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence swipe = new Sequence(input, 0);
        swipe.addAction(input.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), location.x, location.y));
        swipe.addAction(input.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(input.createPointerMove(Duration.ofSeconds(1), PointerInput.Origin.viewport(), location.x, location.y));
        swipe.addAction(input.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        ((AppiumDriver) ContextManager.getDriver()).perform(ImmutableList.of(swipe));
    }

//    public void click(By byEl) {
//        new WebDriverWait((AppiumDriver) ContextManager.getDriver(), Duration.ofSeconds(20)).until(ExpectedConditions.presenceOfElementLocated(byEl)).click();
//    }
//
//    public void sendkeys(By byEl, String text) {
//        waitForEl(byEl);
//        (AppiumDriver) ContextManager.getDriver().findElement(byEl).sendkeys(text);
//    }

//    public void waitForEl(By byEl) {
//        new WebDriverWait((AppiumDriver) ContextManager.getDriver(), Duration.ofSeconds(20)).until(ExpectedConditions.presenceOfElementLocated(byEl));
//    }

//        public static void scroll (ScrollDirection dir,double scrollRatio){
//            if (scrollRatio < 0 || scrollRatio > 1) {
//                throw new Error("Scroll distance must be between 0 and 1");
//            }
//            Dimension size = getWindowSize();
//            Point midPoint = new Point((int) (size.width * 0.5), (int) (size.height * 0.5));
//            int top = midPoint.y - (int) ((size.height * scrollRatio) * 0.5);
//            int bottom = midPoint.y + (int) ((size.height * scrollRatio) * 0.5);
//            int left = midPoint.x - (int) ((size.width * scrollRatio) * 0.5);
//            int right = midPoint.x + (int) ((size.width * scrollRatio) * 0.5);
//            if (dir == ScrollDirection.UP) {
//
//            }
//        }


    /**
     * This Function is to Scroll to element
     *
     * @author Prashanth
     * @param: Mobile Element & String
     */
    public void scrollToMobileElement(WebElement element, String scrollCount) {
        try {
            int count = Integer.parseInt(scrollCount);
            for (int i = 0; i < count; i++) {
                if (isElementDisplayed(element)) {
                    break;
                } else {
                    //swipeDown();
                    scroll(ScrollDirection.DOWN, SCROLL_RATIO);
                }
            }
        } catch (Exception e) {
            System.out.println("Scroll to mobile element failed");
        }
    }

}
