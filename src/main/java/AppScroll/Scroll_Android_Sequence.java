package AppScroll;


import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;


public class Scroll_Android_Sequence {

    public AppiumDriver driver;

    @BeforeTest
    public void setUp() {


        UiAutomator2Options opt = new UiAutomator2Options();
        opt.setDeviceName("emulator-5554")
                .setPlatformVersion("12.0")
                .setPlatformName("Android")
                .setAutomationName("UiAutomator2")
                .setAppPackage("com.cricbuzz.android")
                .setAppActivity("com.cricbuzz.android.lithium.app.view.activity.NyitoActivity")
                .setNoReset(true)
                .setIgnoreHiddenApiPolicyError(true);

        try {
            driver = new AppiumDriver(new URL("http://localhost:4723/wd/hub"), opt);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void testGestureSwipe() {
        verticalScroll(driver);
        horizontalScroll(driver);

    }

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

    }

    public void horizontalScroll(AppiumDriver driver){
        //Creating horizontal scroll event
        //Scrollable element

        WebElement element = driver.findElement(AppiumBy.id("com.cricbuzz.android:id/vp_home_content"));

        int centerY = element.getRect().y+(element.getSize().width/2);
        double startX = element.getRect().x+(element.getSize().height*0.8);
        double endX = element.getRect().x+(element.getSize().height*0.2);

        //Type of Pointer input
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH,"finger");

        //Creating sequence object to add actions
        Sequence swipe = new Sequence(finger, 1);

        //Move finger into starting position
        swipe.addAction(finger.createPointerMove(Duration.ofSeconds(5),PointerInput.Origin.viewport(), (int)startX, centerY));

        //Finger comes down into contact with screen
        swipe.addAction(finger.createPointerDown(0));

        //Finger moves to end position
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(700), PointerInput.Origin.viewport(),(int) endX, centerY));

        //Get up finger from screen
        swipe.addAction(finger.createPointerUp(0));

        //Perform the actions
        driver.perform(Arrays.asList(swipe));

        try {
            Thread.sleep(3000);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }


//        driver.findElement(AppiumBy.id("com.cricbuzz.android:id/vp_home_content")).click();
//        driver.findElement(AppiumBy.id("com.cricbuzz.android:id/main_content")).click();
//        Thread.sleep(2000);
//
//        RemoteWebElement verticalSlider = (RemoteWebElement) driver.findElement(AppiumBy.id("com.cricbuzz.android:id/main_content"));
//        RemoteWebElement horizontalSlider = (RemoteWebElement) driver.findElement(AppiumBy.id("com.cricbuzz.android:id/main_content"));
//
//        driver.executeScript("gesture: swipe", ImmutableMap.of("elementId", horizontalSlider.getId(), "percentage", 50, "direction", "left"));
//        Thread.sleep(2000);
//        driver.executeScript("gesture: swipe", ImmutableMap.of("elementId", verticalSlider.getId(), "percentage", 50, "direction", "up"));
//        Thread.sleep(2000);
//        driver.quit();

}

