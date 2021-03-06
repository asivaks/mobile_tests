//obsolete, replaced by LocalMobileDriver

package drivers;

import com.codeborne.selenide.WebDriverProvider;
import config.GalaxyA51Config_obsolete;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.AutomationName;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import static org.apache.commons.io.FileUtils.copyInputStreamToFile;

public class GalaxyA51MobileDriver_obsolete implements WebDriverProvider {

    @Override
    public WebDriver createDriver(Capabilities capabilities) {
        GalaxyA51Config_obsolete config = ConfigFactory.create(GalaxyA51Config_obsolete.class, System.getProperties());
        File app = getApp();
        UiAutomator2Options options = new UiAutomator2Options();
        options.merge(capabilities);
        options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);

        //options.setPlatformName("Android");
        options.setPlatformName(config.platformName());

        //"this shit doesn't affect anything, it will take just 1st device with fitting platform version
        //options.setDeviceName("RZ8R228QXTB");
        options.setDeviceName(config.deviceName());

        //options.setPlatformVersion("11.0");
        options.setPlatformVersion(config.platformVersion());

        options.setApp(app.getAbsolutePath());

        //GET PACKAGE AND ACTIVITY
        //https://www.automationtestinghub.com/apppackage-and-appactivity-name/
        //OR
        //adb shell dumpsys window | grep -E 'mCurrentFocus'

        options.setAppPackage("org.wikipedia.alpha");
        options.setAppActivity("org.wikipedia.main.MainActivity");

        System.out.println("options= " + options);

        return new AndroidDriver(getAppiumServerUrl(), options);

    }

    public static URL getAppiumServerUrl() {
        try {
            return new URL("http://localhost:4723/wd/hub");
        } catch (MalformedURLException exception) {
            throw new RuntimeException(exception);
        }


    }

    private File getApp() {
        String appUrl = "https://github.com/wikimedia/apps-android-wikipedia/releases/download/latest/app-alpha-universal-release.apk";
        String appPath = "src/test/resources/apps/app-alpha-universal-release.apk";

        File app = new File(appPath);
        if (!app.exists()) {
            try (InputStream inputStream = new URL(appUrl).openStream()) {
                copyInputStreamToFile(inputStream , app);
            }
            catch (IOException exception) {
                throw new AssertionError("Failed to download app", exception);
            }
        }
        return app;
    }

}
