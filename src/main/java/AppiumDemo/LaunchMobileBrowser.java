package AppiumDemo;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class LaunchMobileBrowser {

    @Test
    public void launchChrome() throws MalformedURLException, InterruptedException {

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("deviceName", "fd9d07b4");
        caps.setCapability("automationName","UiAutomator2");
        caps.setCapability("platform version", "11");
        caps.setCapability("browserName","Chrome");

        AppiumDriver<WebElement> driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
        driver.get("https://opensource-demo.orangehrmlive.com/");
        Thread.sleep(3000);
        driver.quit();

    }
}
