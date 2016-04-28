package javase.t02.demo;

import javase.t02.abstractapp.App;
import javase.t02.abstractfactory.AppFactory;
import javase.t02.concretefactory.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Demonstration of Abstract Factory pattern using
 * Created by Yury Vislobodsky on 24.04.2016.
 */
public class AppDemo {
    private static Logger logger = LoggerFactory.getLogger(AppDemo.class);

    private AppDemo() {}

    public static String appMessage(String fileName, AppFactory appFactory) {
        App app = appFactory.createApp();
        app.setFileName(fileName);
        return app.getMessage();
    }

    public static void main(String[] args) {
        logger.info(appMessage("MyWindowsApp", new WindowsAppFactory()));
        logger.info(appMessage("MyAndroidApp", new AndroidAppFactory()));
        logger.info(appMessage("MyMacOSXApp", new MacOSXAppFactory()));
    }
}
