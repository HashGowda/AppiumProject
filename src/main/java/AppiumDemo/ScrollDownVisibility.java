package AppiumDemo;

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

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofSeconds;

public class ScrollDownVisibility {
    AndroidDriver<WebElement> driver;

    @Test
    public void launchApp() throws MalformedURLException, InterruptedException {

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("deviceName", "Android name");
        caps.setCapability("automationName", "UiAutomator2");
        caps.setCapability("platform version", "11");
        caps.setCapability("appPackage", "com.cricbuzz.android");
        caps.setCapability("appActivity", "com.cricbuzz.android.lithium.app.view.activity.NyitoActivity");

        driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
        Thread.sleep(3000);
        driver.findElement(By.id("com.cricbuzz.android:id/tab_more")).click();
        Thread.sleep(3000);
        scrollDownToElement(10, driver.findElement(By.id("com.cricbuzz.android:id/tab_more")));
    }

    @Test
    void scrollDown(){
        Dimension dimension = driver.manage().window().getSize();
        int width = dimension.getWidth();
        int height = dimension.getHeight();
        int x = (width/2);
        int startPoint = (int)(height*0.9);
        int endPoint = (int)(width*0.5);
        TouchAction action = new TouchAction<>(driver);
        action.press(point(x, startPoint)).waitAction(waitOptions(ofSeconds(3))).moveTo(point(x, endPoint)).release().perform();
    }

    void scrollDownToElement(int count, WebElement element) {
        try {
            for (int i=0; i<count;i++){
                if (element.isDisplayed()){
                    break;
                } else {
                    scrollDown();
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
