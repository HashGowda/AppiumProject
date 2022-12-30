package AppiumDemo;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class SearchElement {

    AndroidDriver<WebElement> driver;

    @Test
    public void launchApp() throws InterruptedException, MalformedURLException {

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("deviceName", "Android name");
        caps.setCapability("automationName", "UiAutomator2");
        caps.setCapability("platform version", "11");
        caps.setCapability("appPackage", "in.amazon.mShop.android.shopping");
        caps.setCapability("appActivity", "com.amazon.mShop.splashscreen.StartupActivity");

        driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
        Thread.sleep(3000);

        driver.findElement(By.id("in.amazon.mShop.android.shopping:id/sso_continue")).click();
        Thread.sleep(3000);

        WebElement search = driver.findElement(By.id("in.amazon.mShop.android.shopping:id/chrome_search_hint_view"));
        search.sendKeys("Mobiles");
        driver.findElement(By.id("in.amazon.mShop.android.shopping:id/rs_search_plate")).click();
        Thread.sleep(3000);
        driver.quit();
    }
}
