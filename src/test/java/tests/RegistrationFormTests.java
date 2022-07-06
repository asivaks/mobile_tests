package tests;

import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.back;
import static io.qameta.allure.Allure.step;

@Tag("android")
public class RegistrationFormTests extends TestBase{

    @DisplayName("Check registration form")
    @Test
    void registrationFormTest() {
        final String login = "MyComplexName";
        final String password = "MyPassword";
        final String email = "myemail@nospam.xxx";
        back();
        step("Tap on Edits", () -> {
            $(AppiumBy.xpath("//android.widget.TextView[@text='Edits']")).click();
        });
        step("Tap on LOG IN / JOIN WIKIPEDIA", () -> {
                    $(AppiumBy.id("org.wikipedia.alpha:id/positiveButton")).click();
                }
        );
        step("Make sure that Registration Form was opened", () -> {
            $(AppiumBy.xpath("//android.widget.TextView[@text='Create an account']"))
                    .shouldHave(text("Create an account"));
        });

        step("Fill Registration Form", () -> {
            $(AppiumBy.xpath("//android.widget.EditText[@text='Username']")).sendKeys(login);
            $(AppiumBy.xpath("//android.widget.EditText[@text='Password']")).sendKeys(password);
            $(AppiumBy.xpath("//android.widget.EditText[@text='Repeat password']")).sendKeys(password);
            $(AppiumBy.xpath("//android.widget.EditText[@text='Email (Optional)']")).sendKeys(email);
        });

        step("Tap Next", () -> {
            $(AppiumBy.xpath("//android.widget.Button[@text='NEXT']")).click();
        });
        step("Check captcha", () -> {
            $(AppiumBy.id("org.wikipedia.alpha:id/request_account_text")).shouldHave(text("Can't see the image? Request an account"));
            $(AppiumBy.id("org.wikipedia.alpha:id/captcha_image")).shouldBe(visible);
            $(AppiumBy.id("org.wikipedia.alpha:id/captcha_submit_button")).shouldBe(visible).shouldBe(disabled);
        });

    }

}
