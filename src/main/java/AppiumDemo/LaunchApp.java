package AppiumDemo;

import com.sun.org.apache.bcel.internal.generic.DREM;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofSeconds;

public class LaunchApp {

    AppiumDriver<MobileElement> driver;
    public static  WebDriverWait wait;

    @BeforeSuite
    public void launchAppFromAppium() throws MalformedURLException, InterruptedException {

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("deviceName", "fd9d07b4");
        caps.setCapability("automationName","UiAutomator2");
        caps.setCapability("platform version", "12");
        caps.setCapability("ignoreHiddenApiPolicyError", "true");
        caps.setCapability("noReset", "true");
        caps.setCapability("appPackage", "com.cricbuzz.android");
        caps.setCapability("appActivity", "com.cricbuzz.android.lithium.app.view.activity.NyitoActivity");
        driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), caps);


    }

    void scrollUp() throws InterruptedException {
        Thread.sleep(2000);
        Dimension dimension = driver.manage().window().getSize();
        int height = dimension.getHeight();
        int width = dimension.getWidth();
        int x = width / 2;
        int start_y = (int) (height * 0.8);
        int end_y = (int) (height * 0.2);
        TouchAction action = new TouchAction(driver);
        action.press(point(x, start_y)).waitAction(waitOptions(ofSeconds(3))).moveTo(point(x, end_y)).release().perform();
    }

    void scrollDown() throws InterruptedException {
        Thread.sleep(2000);
        Dimension dimension = driver.manage().window().getSize();
        int height = dimension.getHeight();
        int width = dimension.getWidth();
        int x = width / 2;
        int start_y = (int) (height * 0.2);
        int end_y = (int) (height * 0.8);
        TouchAction action = new TouchAction(driver);
        action.press(point(x, start_y)).waitAction(waitOptions(ofSeconds(3))).moveTo(point(x, end_y)).release().perform();
    }
    @Test(priority = 0)
    public void explicitWait(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.cricbuzz.android:id/txt_match_num")));
    }

    @Test(priority = 1)
    void currentMatch()  {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.cricbuzz.android:id/txt_match_num")));
        driver.findElement(By.id("com.cricbuzz.android:id/txt_match_num")).click();
    }

    @Test(priority = 3)
    void scoreCard() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.LinearLayout[@content-desc='Scorecard']/android.widget.TextView")));
        driver.findElement(By.xpath("//android.widget.LinearLayout[@content-desc='Scorecard']/android.widget.TextView")).click();
        scrollUp();
    }

    @Test(priority = 2)
    void clickOnMore() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.cricbuzz.android:id/txt_more")));
        driver.findElement(By.id("com.cricbuzz.android:id/txt_more")).click();
        scrollDown();
    }

    @Test(priority = 4)
    void clickOnPlayer(){
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//*[@*='com.cricbuzz.android:id/txtName'])[6]")));
        driver.findElement(By.xpath("(//*[@*='com.cricbuzz.android:id/txtName'])[6]")).click();
    }

//    void scrollToElement(int count, WebElement element) {
//        try {
//            for (int i = 0; i < count; i++) {
//                if (element.isDisplayed()) {
//                    break;
//                } else {
//                    scroll();
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
