package tests;

import com.codeborne.selenide.CollectionCondition;
import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

@Tag("android")
public class SearchTests extends TestBase {

    @Tag("searchTest")
    @DisplayName("Search test - make sure that there are some results")
    @Test
    void searchTest() {
        back();
        step("Type search", () -> {
            $(AppiumBy.id("org.wikipedia.alpha:id/search_container")).click();
            $(AppiumBy.id("org.wikipedia.alpha:id/search_src_text")).sendKeys("BrowserStack");
        });

        step("Verify content found", () -> {
            $$(AppiumBy.id("org.wikipedia.alpha:id/page_list_item_title")).shouldHave(CollectionCondition.sizeGreaterThan(0));
        });
    }

    @Tag("searchTitleTest")
    @DisplayName("Search test - make sure that title = request")
    @Test
    void searchTitleTest() {
        final String searchRequest = "BrowserStack";
        back();
        step("Enter search field", () -> {
            $(AppiumBy.id("org.wikipedia.alpha:id/search_container")).click();
        });
        step("Search", () -> {
            $(AppiumBy.id("org.wikipedia.alpha:id/search_src_text")).sendKeys(searchRequest);
        });

        step("Verify content found and = search request", () -> {
            $(AppiumBy.xpath("//android.widget.TextView[@text='"+searchRequest+"']")).shouldHave(text(searchRequest));
        });
    }


    @DisplayName("Search Field should have text")
    @Test
    void searchFieldTest() {

        back();
        //switchTo().alert().accept(); //accept alert
        step("Search Field should have text", () -> {
            $(AppiumBy.id("org.wikipedia.alpha:id/search_container")).click();
            $(AppiumBy.id("org.wikipedia.alpha:id/search_src_text")).shouldHave(text("Search Wikipedia"));
        });
    }

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