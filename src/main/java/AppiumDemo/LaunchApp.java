package AppiumDemo;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofSeconds;

public class LaunchApp {

    AppiumDriver<WebElement> driver;

    @Test
    public void launchAppFromAppium() throws MalformedURLException, InterruptedException {

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("deviceName", "Android name");
        caps.setCapability("automationName","UiAutomator2");
        caps.setCapability("platform version", "11");
        caps.setCapability("appPackage", "com.cricbuzz.android");
        caps.setCapability("appActivity", "com.cricbuzz.android.lithium.app.view.activity.NyitoActivity");

        driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
        Thread.sleep(3000);
        driver.findElement(By.id("com.cricbuzz.android:id/tab_more")).click();
        Thread.sleep(3000);
        scrollToElement(10, driver.findElement(By.id("com.cricbuzz.android:id/tab_more")));
    }

//    @Test
    void scroll() {
        Dimension dimension = driver.manage().window().getSize();
        int height = dimension.getHeight();
        int width = dimension.getWidth();
        int x = width / 2;
        int start_y = (int) (height * 0.6);
        int end_y = (int) (height * 0.3);
        TouchAction action = new TouchAction(driver);
        action.press(point(x, start_y)).waitAction(waitOptions(ofSeconds(3))).moveTo(point(x, end_y)).release().perform();
    }


    void scrollToElement(int count, WebElement element) {
        try {
            for (int i = 0; i < count; i++) {
                if (element.isDisplayed()) {
                    break;
                } else {
                    scroll();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
