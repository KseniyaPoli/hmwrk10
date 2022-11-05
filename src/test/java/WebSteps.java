import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class WebSteps {
    @Step("Открыть глазвную страницу")
    public void openMainPage(){
        open("https://github.com");
    }
    @Step("Найти в поиске")
    public void searchRepo(String repo){
        $(".header-search-input").click();
        $(".header-search-input").sendKeys(StepsTest.Repo);
        $(".header-search-input").submit();
    }
    @Step("Кликнуть по ссылке")
    public void clickOnRepoLink(String repo){
        $(linkText(StepsTest.Repo)).click();
    }
    @Step("Найти раздел Issues")
    public void openIssues(){
        $("#issues-tab").click();
    }
    @Step("Найти Issue с номером {IssueNum}")
    public void checkIssueNum(int issueNum){
            $(withText("#" + issueNum)).should(Condition.exist);
    }
    @Attachment(value = "Screenshot", type = "image/png", fileExtension = "png")
    public byte[] takeScreenshot() {
        return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }
}

