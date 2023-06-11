package AppScroll;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class ZoomIn_Out {

    public AppiumDriver driver;

    @BeforeTest
    public void setUp () {


        UiAutomator2Options opt = new UiAutomator2Options();
        opt.setDeviceName("emulator-5554")
                .setPlatformVersion("12.0")
                .setPlatformName("Android")
                .setAutomationName("UiAutomator2")
                .setAppPackage("com.oneplus.gallery")
                .setAppActivity("com.oneplus.gallery.app.MainActivity")
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
    public void zoomIn(){

        driver.findElement(AppiumBy.)

    }
}
