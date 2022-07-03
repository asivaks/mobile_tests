package drivers;

import com.codeborne.selenide.WebDriverProvider;
import config.BrowserstackConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;

public class BrowserstackMobileDriver implements WebDriverProvider {

    //BrowserstackConfig config = ConfigFactory.create(BrowserstackConfig.class, System.getProperties());
    //MAYBE SHOULD BE MOVED HERE?

    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        MutableCapabilities mutableCapabilities = new MutableCapabilities();
        mutableCapabilities.merge(capabilities);

        BrowserstackConfig config = ConfigFactory.create(BrowserstackConfig.class, System.getProperties());

        String browserstackLogin = config.browserstackLogin();
        String browserstackPassword = config.browserstackPassword();
        String app = config.app();
        String device = config.device();
        String osVersion = config.osVersion();
        String project = config.project();
        String build = config.build();
        String name = config.name();

        //set access credentials
        mutableCapabilities.setCapability("browserstack.user", browserstackLogin);
        mutableCapabilities.setCapability("browserstack.key", browserstackPassword);

        // Set URL of the application under test
        mutableCapabilities.setCapability("app", app); //wikipedia app

        // Specify device and os_version for testing
        mutableCapabilities.setCapability("device", device);
        mutableCapabilities.setCapability("os_version", osVersion);

        // Set other BrowserStack capabilities
        mutableCapabilities.setCapability("project", project);
        mutableCapabilities.setCapability("build", build);
        mutableCapabilities.setCapability("name", name);

        System.out.println("mutableCapabilities= " + mutableCapabilities.asMap());

        return new RemoteWebDriver(getBrowserstackUrl(), mutableCapabilities);
    }

    public static URL getBrowserstackUrl() {
        try {
            return new URL("http://hub.browserstack.com/wd/hub");
        } catch (MalformedURLException exception) {
            throw new RuntimeException(exception);
        }
    }
}
