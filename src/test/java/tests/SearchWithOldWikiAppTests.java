package tests;

import com.codeborne.selenide.CollectionCondition;
import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import samples.browserstack.TestBase;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.qameta.allure.Allure.step;

public class SearchWithOldWikiAppTests extends TestBase {

    @Disabled
    @Test
    void searchTest() {

        step("Type search", () -> {
            $(AppiumBy.accessibilityId("Search Wikipedia")).click();
//        AndroidElement searchElement = (AndroidElement) new WebDriverWait(driver, 30).until(
//                ExpectedConditions.elementToBeClickable(
//                        MobileBy.AccessibilityId("Search Wikipedia")));
//        searchElement.click();


            $(AppiumBy.id("org.wikipedia.alpha:id/search_src_text")).sendKeys("BrowserStack");
//        AndroidElement insertTextElement = (AndroidElement) new WebDriverWait(driver, 30).until(
//                ExpectedConditions.elementToBeClickable(
//                        MobileBy.id("org.wikipedia.alpha:id/search_src_text")));
//        insertTextElement.sendKeys("BrowserStack");
//        Thread.sleep(5000);
        });

        step("Verify content found", () -> {
            $$(AppiumBy.className("android.widget.TextView")).shouldHave(CollectionCondition.sizeGreaterThan(0));
//        List<AndroidElement> allProductsName = driver.findElementsByClassName(
//                "android.widget.TextView");
//        assert (allProductsName.size() > 0);
        });


//        driver.quit();


    }

}