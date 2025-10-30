package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ResultPage extends BasePage {

    @FindBy(xpath = "//div[@id='dismissible']//a[@id='thumbnail']")
    private List<WebElement> searchResults;

    public ResultPage(WebDriver driver) {
        super(driver);
    }

    public VideoPage selectVideoByIndex(int index) {
        clickOnElementByIndex(searchResults, index);
        return new VideoPage(driver);
    }


}
