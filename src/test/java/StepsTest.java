import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class StepsTest {
    public static final String Repo = "postmanlabs/postman-app-support";
    public static final int IssueNum = 11410;

    @Test
    public void checkIssueByStepsTest() {

        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открыть главную страницу", () -> {

            open("https://github.com");
        });
        step("Найти в поиске" + Repo, () -> {
            $(".header-search-input").click();
            $(".header-search-input").sendKeys(Repo);
            $(".header-search-input").submit();
        });
        step("Кликнуть по ссылке -" + Repo, () -> {
            $(linkText(Repo)).click();
        });
        step("Открыть раздел Issues", () -> {
            $("#issues-tab").click();
        });
        step("Проверить issue с номером " + IssueNum, () -> {
            $(withText("#" + IssueNum)).should(Condition.exist);
        });


    }
    @Test
    public void testAnnotatedStep() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        WebSteps steps = new WebSteps();

        steps.openMainPage();
        steps.searchRepo(Repo);
        steps.clickOnRepoLink(Repo);
        steps.openIssues();
        steps.checkIssueNum(IssueNum);

    }

}


