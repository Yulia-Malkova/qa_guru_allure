package guru.qa;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;
import io.qameta.allure.Allure;
import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class SimpleSelenideTestWithListener {

    @Test
    @Feature("Issue в репозитории")
    @Story("Поиск Issue")
    @Owner("yulia-malkova")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Наличие issue #1 в репозитории Yulia-Malkova/demoqa-21")
    public void SelenideSearchTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        open("https://github.com");
        $(".search-input-container").click();
        $("#query-builder-test").setValue(("Yulia-Malkova/demoqa-21")).submit();
        $(linkText("Yulia-Malkova/demoqa-21")).click();
        $("#issues-tab").click();
        $(withText("#1")).should(exist);

    }

}
