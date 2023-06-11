package AppScroll;


import com.aventstack.extentreports.gherkin.model.And;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;


public class AndroidTest {

    @Test
    public void myDemoApp() throws MalformedURLException, InterruptedException {

        UiAutomator2Options opt = new UiAutomator2Options();
        opt.setDeviceName("emulator-5554")
                .setPlatformVersion("12.0")
                .setPlatformName("Android")
                .setAutomationName("UiAutomator2")
                .setApp(System.getProperty("user.dir") + "/apps/Android-MyDemoAppRN.1.3.0.build-244.apk")
                .setNoReset(true)
                .setIgnoreHiddenApiPolicyError(true);

        AndroidDriver driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), opt);


        //LoginTest
        //loginTest(driver);

        //Tap element
        //tap(driver);

        //DoubleTap
        //doubleTap(driver);

        //Zoom element
        zoom(driver);

    }

    //To perform LoginTest

    private void loginTest(AndroidDriver driver) throws InterruptedException {

        driver.findElement(AppiumBy.accessibilityId("open menu")).click();
        Thread.sleep(2000);
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(e -> e.findElement(AppiumBy.xpath("//android.view.ViewGroup[@content-desc=\"menu item log in\"]")));
        driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@content-desc=\"menu item log in\"]"))
                .click();
        Thread.sleep(2000);
        driver.findElement(AppiumBy.accessibilityId("Username input field")).sendKeys("Test User");
        driver.findElement(AppiumBy.accessibilityId("Password input field")).sendKeys("password");
        driver.findElement(AppiumBy.accessibilityId("Login button")).click();

    }


    //To perform Tap

    private void tap(AndroidDriver driver) {

        WebElement openMenu = driver.findElement(AppiumBy.accessibilityId("open menu"));

        Point location = openMenu.getLocation();
        Dimension size = openMenu.getSize();

        Point centerOfElement = getCenterOfElement(location, size);

        PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence sequence = new Sequence(finger1, 1)
                .addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerOfElement))
                .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger1, Duration.ofMillis(200)))
                .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(sequence));
    }

    //To perform DoubleTap

    private void doubleTap(AndroidDriver driver) {

        WebElement openMenu = driver.findElement(AppiumBy.accessibilityId("open menu"));

        Point location = openMenu.getLocation();
        Dimension size = openMenu.getSize();

        Point centerOfElement = getCenterOfElement(location, size);

        PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence sequence = new Sequence(finger1, 1)
                .addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerOfElement))
                .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger1, Duration.ofMillis(100)))
                .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger1, Duration.ofMillis(100)))
                .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(sequence));

    }


    //To perform zoom
    private void zoom(AndroidDriver driver) {

        driver.findElement(AppiumBy.accessibilityId("open menu")).click();
        driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@content-desc=\"menu item drawing\"]")).click();
        WebElement zoomElement = driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@content-desc=\"menu item drawing\"]"));

        Point centerOfElement = getCenterOfElement(zoomElement.getLocation(), zoomElement.getSize());

        PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        PointerInput finger2 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");

        Sequence sequence = new Sequence(finger1, 1)
                .addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerOfElement))
                .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger1, Duration.ofMillis(200)))
                .addAction(finger1.createPointerMove(Duration.ofMillis(200), PointerInput.Origin.viewport(), centerOfElement.getX() + 100, centerOfElement.getY() - 100))
                .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        Sequence sequence2 = new Sequence(finger2, 1)
                .addAction(finger2.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerOfElement))
                .addAction(finger2.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger2, Duration.ofMillis(200)))
                .addAction(finger2.createPointerMove(Duration.ofMillis(200), PointerInput.Origin.viewport(), centerOfElement.getX() - 100, centerOfElement.getY() + 100))
                .addAction(finger2.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Arrays.asList(sequence, sequence2));

    }


    @Test
    public void apiDemoApp() throws MalformedURLException {

        UiAutomator2Options opt = new UiAutomator2Options();
        opt.setDeviceName("emulator-5554")
                .setPlatformVersion("12.0")
                .setPlatformName("Android")
                .setAutomationName("UiAutomator2")
                .setApp(System.getProperty("user.dir") + "/apps/ApiDemos-debug.apk")
                .setNoReset(true)
                .setIgnoreHiddenApiPolicyError(true);

        AndroidDriver driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), opt);


        //LongPress
        //longPress(driver);
        //new Actions(driver).clickAndHold(element).perform();

        //ScrollDown
        //scrollDown(driver);

        //DragAndDrop
        dragDrop(driver);

    }


    //To perform LongPress

    private void longPress(AndroidDriver driver) {

        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        driver.findElement(AppiumBy.accessibilityId("Expandable Lists")).click();
        driver.findElement(AppiumBy.accessibilityId("1. Custom Adapter")).click();
        WebElement element = driver.findElement(AppiumBy.xpath(".//*[@text='People Names']"));

        Point location = element.getLocation();
        Dimension size = element.getSize();

        Point centerOfElement = getCenterOfElement(location, size);

        PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence sequence = new Sequence(finger1, 1)
                .addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerOfElement))
                .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger1, Duration.ofSeconds(2)))
                .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(sequence));
    }


    //To perform ScrollDown

    private void scrollDown(AndroidDriver driver) {

        driver.findElement(AppiumBy.accessibilityId("Views")).click();

        Dimension size = driver.manage().window().getSize();
        int startX = size.getWidth() / 2;
        int startY = size.getHeight() / 2;
        int endY = (int) (size.getHeight() * 0.25);

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence sequence = new Sequence(finger, 1)
                .addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
                .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger, Duration.ofMillis(200)))
                .addAction(finger.createPointerMove(Duration.ofMillis(100), PointerInput.Origin.viewport(), startX, endY))
                .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(sequence));

    }

    //To perform DragAndDrop
    private void dragDrop(AndroidDriver driver) {

        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        driver.findElement(AppiumBy.accessibilityId("Drag and Drop")).click();

        WebElement source = driver.findElement(AppiumBy.id("io.appium.android.apis:id/drag_dot_1"));
        WebElement target = driver.findElement(AppiumBy.id("io.appium.android.apis:id/drag_dot_2"));

        Point sourceElementCenter = getCenterOfElement(source.getLocation(), source.getSize());
        Point targetElementCenter = getCenterOfElement(target.getLocation(), target.getSize());

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence sequence = new Sequence(finger, 1)
                .addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), sourceElementCenter))
                .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger, Duration.ofMillis(200)))
                .addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), targetElementCenter))
                .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(sequence));

    }


    private Point getCenterOfElement(Point location, Dimension size) {

        return new Point(location.getX() + size.getWidth() / 2, location.getY() + size.getHeight() / 2);
    }
}


    //Vertical Scroll

/*    @Test
    public void verticalScroll(AppiumDriver driver) {
        //Creating Vertical Scroll Event
        //Scrollable Element
        WebElement element = driver.findElement(AppiumBy.id("com.cricbuzz.android:id/rv_main"));

        int centerX = element.getRect().x+(element.getSize().width/2);
        double startY = element.getRect().y+(element.getSize().height*0.8);
        double endY = element.getRect().y+(element.getSize().height*0.2);

        //Type of Pointer input
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH,"finger");

        //Creating sequence object to add actions
        Sequence swipe = new Sequence(finger, 1);

        //Move finger into starting position
        swipe.addAction(finger.createPointerMove(Duration.ofSeconds(5),PointerInput.Origin.viewport(), centerX, (int) startY));

        //Finger comes down into contact with screen
        swipe.addAction(finger.createPointerDown(0));

        //Finger moves to end position
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(700), PointerInput.Origin.viewport(), centerX,(int) endY));

        //Get up finger from screen
        swipe.addAction(finger.createPointerUp(0));

        //Perform the actions
        driver.perform(Arrays.asList(swipe));

    }*/


    //IOS Launch Test

/*    @Test
    public void iosLaunchTest() throws MalformedURLException, InterruptedException {

        XCUITestOptions opt = new XCUITestOptions();
        opt.setDeviceName("iPhone 13")
                .setPlatformName("iOS")
                .setAutomationName("XCuiTest")
                .setApp(System.getProperty("user.dir") + "/apps/iOS-Real-Device-MyRNDemoApp.1.3.0-162.ipa")
                .setNoReset(true);

        IOSDriver driver = new IOSDriver(new URL("http://localhost:4723/wd/hub"), opt);
        Thread.sleep(2000);

    }*/

