package AppScroll;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.testng.annotations.Test;

import java.io.File;
import java.util.HashMap;

import static io.appium.java_client.service.local.flags.GeneralServerFlag.BASEPATH;

public class AppiumServerDemo {

    private static AppiumDriverLocalService service;

    @Test(priority = 0)
    public static void startAppiumServer() {
        AppiumServiceBuilder builder = new AppiumServiceBuilder();

        builder.withIPAddress("127.0.0.1")
                .usingPort(4723)
                .usingAnyFreePort()
                .usingDriverExecutable(new File("C:\\Program Files\\nodejs\\node.exe"))
                .withAppiumJS(new File("C:\\Users\\haris\\AppData\\Local\\Programs\\Appium Server GUI\\resources\\app\\node_modules\\appium\\build\\lib\\main.js"))
                .withArgument(BASEPATH, "wd/hub")
                .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
                .withArgument(GeneralServerFlag.LOG_LEVEL,"debug");

        /*
        HashMap<String, String> environment = new HashMap<>();
        environment.put("PATH", "user.dir" + System.getenv("PATH"));
        builder.withEnvironment(environment);
        */

        service = AppiumDriverLocalService.buildService(builder);
        service.start();
    }


    @Test(priority = 1)
    public static void stopAppiumServer() throws InterruptedException {
        Thread.sleep(2000);
        service.stop();
    }
}
