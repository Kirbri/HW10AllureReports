package ru.kirbri.allure;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class SelenideTest extends TestBase{

    @Test
    public void testIssueSearchTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        open("https://github.com");
        $("[data-target=\"qbsearch-input.inputButtonText\"]").click();
        $(".FormControl-input.QueryBuilder-Input.FormControl-medium")
                .setValue("eroshenkoam/allure-example").submit();
        $(linkText("eroshenkoam/allure-example")).click();
        $("#issues-repo-tab-count").click();
        $(withText("#90")).should(Condition.exist);
    }
}
