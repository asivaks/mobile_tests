package tests.local;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import drivers.LocalEmulatorMobileDriver;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static io.qameta.allure.Allure.step;

public class TestBase {
    @BeforeAll
    public static void setup() {
        Configuration.browser = LocalEmulatorMobileDriver.class.getName();
        Configuration.browserSize = null;   //crutch but mandatory should be

    }

    @BeforeEach
    public void startDriver() {
        addListener("AllureSelenide", new AllureSelenide());
        open();                              //start app

    }

    @AfterEach
    public void afterEach() {
        //String sessionId = sessionId(); //no local video recording
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        //closeWebDriver();
        step("Close driver", Selenide::closeWebDriver);

        //Attach.video(sessionId);  //no local video recording

    }
}
