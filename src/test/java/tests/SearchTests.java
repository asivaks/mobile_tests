package tests;

import com.codeborne.selenide.CollectionCondition;
import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

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

}