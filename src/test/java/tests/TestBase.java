package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import drivers.BrowserstackMobileDriver;
import drivers.GalaxyA51MobileDriver;
import drivers.LocalEmulatorMobileDriver;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static helpers.Attach.sessionId;
import static io.qameta.allure.Allure.step;

public class TestBase {

    static String host = System.getProperty("host", "emulator");

    @BeforeAll
    public static void setup() throws Exception {
        //Configuration.browser = GalaxyA51MobileDriver.class.getName();

        System.out.println("host= " + host);

        switch (host) {
            case ("emulator"):
                Configuration.browser = LocalEmulatorMobileDriver.class.getName();
                break;
            case ("GalaxyA51"):
                Configuration.browser = GalaxyA51MobileDriver.class.getName();
                break;
            case ("browserstack"):
                Configuration.browser = BrowserstackMobileDriver.class.getName();
                break;
            default:
                throw new Exception("unknown host=" + host);

        }

        Configuration.browserSize = null;   //crutch but mandatory should be

    }

    @BeforeEach
    public void startDriver() {
        addListener("AllureSelenide", new AllureSelenide());
        open();                              //start app

    }

    @AfterEach
    public void afterEach() throws Exception {
        //no local video recording
        String sessionId = null;
        if (host.equals("browserstack")) {
            sessionId = sessionId();
        }

        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        //closeWebDriver();
        step("Close driver", Selenide::closeWebDriver);

        //no local video recording
        if (host.equals("browserstack")) {
            if (sessionId.equals(null)) {
                throw new Exception("Session ID should not be null for browserstack");
            }
            Attach.video(sessionId);
        }

    }
}
