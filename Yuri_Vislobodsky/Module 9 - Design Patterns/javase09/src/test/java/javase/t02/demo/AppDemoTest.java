package javase.t02.demo;

import static org.junit.Assert.*;
import org.junit.Test;
import javase.t02.concretefactory.AndroidAppFactory;
import javase.t02.concretefactory.MacOSXAppFactory;
import javase.t02.concretefactory.WindowsAppFactory;

/**
 * Test of AppDemo class, based on app messages comparison
 * Created by Yury Vislobodsky on 24.04.2016.
 */
public class AppDemoTest {

        @Test
        public void testAppDemo() {

            assertTrue("Error in Windows App message",
                    "File MyWindowsApp.exe is placed in the Windows Store".equals(
                    AppDemo.appMessage("MyWindowsApp", new WindowsAppFactory())));

            assertTrue("Error in Android App message",
                    "File MyAndroidApp.apk is placed in the Google Play Market".equals(
                    AppDemo.appMessage("MyAndroidApp", new AndroidAppFactory())));

            assertTrue("Error in MacOSX App message",
                    "File MyMacOSXApp.ipa is placed in the App Store".equals(
                    AppDemo.appMessage("MyMacOSXApp", new MacOSXAppFactory())));
        }
    }