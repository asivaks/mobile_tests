package tests.browserstack;

import com.codeborne.selenide.CollectionCondition;
import io.appium.java_client.AppiumBy;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class SearchTests extends TestBase {

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
//        driver.quit();
    }

    @Disabled
    @Test
    void bottomMenuButtonsTest() {
        back();
        step("Bottom menu should have button Explore", () -> {
            //$(AppiumBy.id("org.wikipedia.alpha:id/main_nav_tab_container")).shouldHave(text("Explore"));

            //$$(byText("some_text")).find(visible).click(); //find 1st visible element with "some_text" and click it
            $(AppiumBy.id("org.wikipedia.alpha:id/main_nav_tab_container")).$(byText("Explore")).shouldBe(visible);


        });
    }

}