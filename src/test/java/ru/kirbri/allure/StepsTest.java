package ru.kirbri.allure;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.logevents.SelenideLogger.step;
import static org.openqa.selenium.By.linkText;

public class StepsTest extends TestBase{

    private static final String REPOSITORY = "eroshenkoam/allure-example";
    private static final int ISSUE = 90;

    @Test
    public void testLambdaStepTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открываем главную страницу", () -> {
                    open("https://github.com");
                });
        step("Ищем репозиторий " + REPOSITORY, () -> {
            $("[data-target=\"qbsearch-input.inputButtonText\"]").click();
            $(".FormControl-input.QueryBuilder-Input.FormControl-medium")
                    .setValue(REPOSITORY).submit();
                });
        step("Кликаем по ссылке репозитория " + REPOSITORY, () -> {
            $(linkText(REPOSITORY)).click();
                });
        step("Открываем таб Issues " + REPOSITORY, () -> {
            $("#issues-repo-tab-count").click();
                });
        step("Проверяем ниличие Issue с номером " + ISSUE, () -> {
            $(withText("#" + ISSUE)).should(Condition.exist);
                });
    }

    @Test
    public void testAnnotatedSteps() {
        WebSteps webSteps = new WebSteps();

        webSteps.openMainPage();
        webSteps.searchForRepository(REPOSITORY);
        webSteps.clickOnRepositoryLink(REPOSITORY);
        webSteps.openIssuesTab();
        webSteps.shouldSeeIssuesWithNumber(ISSUE);
        webSteps.takeScreenshot();
    }
}
