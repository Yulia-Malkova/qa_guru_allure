package guru.qa;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class GithubTestStepsTest {

    private static final String REPOSITORY = "Yulia-Malkova/demoqa-21";
    private static final int ISSUE = 1;

    @Test
    public void selenideSearchLambdaTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());


        step("Открываем главную страницу Github", () -> {
            open("https://github.com");
        });
        step("Ищем репозиторий" + REPOSITORY, ()  -> {
            $(".search-input-container").click();
            $("#query-builder-test").setValue((REPOSITORY)).submit();
        });
        step("Кликаем по ссылке репозитория" + REPOSITORY, () -> {
            $(linkText(REPOSITORY)).click();
        });
        step("Открываем таб Issues", () -> {
            $("#issues-tab").click();
        });
        step("Проверяем наличие Issue с номером " + ISSUE, () ->{
            $(withText("#"+ISSUE)).should(exist);
        });
    }

    @Test
    public void selenideSearchAnnotatedTest(){
        SelenideLogger.addListener("allure", new AllureSelenide());
        WebSteps steps = new WebSteps();
        steps.openMainGithubPage();
        steps.searchForRepository(REPOSITORY);
        steps.clickOnRepositoryLink(REPOSITORY);
        steps.openIssuesTab();
        steps.shouldSeeIssueWithNumber(ISSUE);
    }
    }
