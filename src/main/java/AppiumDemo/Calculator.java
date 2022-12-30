package AppiumDemo;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.flags.AndroidServerFlag;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class Calculator {
    AndroidDriver<WebElement> driver;

    @Test
    public void calculator() throws MalformedURLException, InterruptedException {

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("deviceName", "Android name");
        caps.setCapability("automationName","Appium");
        caps.setCapability("platform version", "11");
        caps.setCapability("appPackage", "com.oneplus.calculator");
        caps.setCapability("appActivity", "com.oneplus.calculator.Calculator");

        driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
        Thread.sleep(3000);

        driver.findElement(By.id("com.oneplus.calculator:id/digit_2")).click();
        driver.findElement(By.id("com.oneplus.calculator:id/op_add")).click();
        driver.findElement(By.id("com.oneplus.calculator:id/digit_8")).click();
        driver.findElement(By.id("com.oneplus.calculator:id/eq")).click();

        String result = driver.findElement(By.id("com.oneplus.calculator:id/result")).getText();

        if(result.equals("10")){
            System.out.println("Test passed...");
        } else {
            System.out.println("Test failed...");
        }
        Thread.sleep(3000);
        driver.quit();
    }
}
