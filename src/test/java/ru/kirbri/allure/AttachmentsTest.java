package ru.kirbri.allure;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.webdriver;
import static io.qameta.allure.Allure.attachment;
import static io.qameta.allure.Allure.step;

public class AttachmentsTest extends TestBase{

    @Test
    public void testLambdaAttachmentsTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открываем главную страницу", () -> {
                    open("https://github.com");
            attachment("Source", webdriver().driver().source());
                });
    }

    @Test
    public void testAnnotatedSteps() {
        WebSteps webSteps = new WebSteps();

        webSteps.openMainPage();
        webSteps.takeScreenshot();
    }
}
