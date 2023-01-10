package AppiumDemo;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofSeconds;

public class VerticalScrolling {
    AndroidDriver<WebElement> driver;

    @Test
    public void launchApp() throws InterruptedException, MalformedURLException {

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("deviceName", "Android name");
        caps.setCapability("automationName", "Appium");
        caps.setCapability("platform version", "11");
        caps.setCapability("appPackage", "in.amazon.mShop.android.shopping");
        caps.setCapability("appActivity", "com.amazon.mShop.splashscreen.StartupActivity");

        driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
        Thread.sleep(3000);

        driver.findElement(By.id("in.amazon.mShop.android.shopping:id/sso_continue")).click();
        Thread.sleep(3000);

//        //VerticalScroll
//        MobileElement ele = driver.findElement(MobileBy.AndroidUIAutomator(
//                "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text"))


        scrollToElement(10, driver.findElement(By.id("in.amazon.mShop.android.shopping:id/cart_count")));
    }

    @Test
    void verticalScroll() {
        Dimension dimension = driver.manage().window().getSize();
        int width = (int)(dimension.getWidth()/2);
        int startPoint = (int) (dimension.getHeight() * 0.9);
        int endPoint = (int) (dimension.getWidth() * 0.5);
        TouchAction action = new TouchAction(driver);
        action.press(point(width, startPoint)).waitAction(waitOptions(ofSeconds(3))).moveTo(point(width, endPoint)).release().perform();
    }

    void scrollToElement(int count, WebElement ele) {
        try {
            for (int i = 1; i < count; i++) {
                if (ele.isDisplayed()) {
                    break;
                } else {
                    verticalScroll();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
