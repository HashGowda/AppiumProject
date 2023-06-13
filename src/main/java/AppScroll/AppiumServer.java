package AppScroll;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

import java.io.File;
import java.net.ServerSocket;

public class AppiumServer {

    static AppiumDriverLocalService service;

    static void setInstance() {

        service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
                .withAppiumJS(new File("C:\\Users\\haris\\AppData\\Local\\Programs\\Appium Server GUI\\resources\\app\\node_modules\\appium\\build\\lib\\main.js"))
                .usingDriverExecutable(new File("C:\\Program Files\\nodejs\\node.exe"))
                //.usingPort(getPort())
                .withIPAddress("127.0.0.1")
                .usingPort(4723)
                .withArgument(GeneralServerFlag.LOCAL_TIMEZONE)
                .withLogFile(new File("E:\\Software Testing\\Java\\MobAppTest\\logs\\AppiumServerLogs.text")));

//        server.start();
//        System.out.println(server.getUrl());
//        System.out.println(server.isRunning());
    }

    static AppiumDriverLocalService getInstance(){
        if (service==null){
            setInstance();
        }
        return service;
    }

    public static void start(){
        getInstance().start();
        System.out.println("Appium server started");
    }

    public static void stop(){
        if (service!=null){
            getInstance().stop();
            System.out.println("Appium server stopped");
        }
    }

   /*
    private static int getPort() throws Exception {
        int port = 0;
        try {
            ServerSocket socket = new ServerSocket(0);
            socket.setReuseAddress(true);
            port = socket.getLocalPort();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return port;
    }
    */

    public static void main(String[] args) throws InterruptedException {
        AppiumServer.start();
        Thread.sleep(2000);
        AppiumServer.stop();
    }
}
