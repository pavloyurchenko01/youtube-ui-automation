package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.security.SecureRandom;
import java.util.List;

public class HomePage extends BasePage {

    @FindBy(xpath = "//input[@name='search_query']")
    private WebElement searchInput;

    @FindBy(xpath = "//button[@aria-label='Search']")
    private WebElement searchButton;

    @FindBy(xpath = "//*[@role='listbox']/div[@role='presentation']")
    private List<WebElement> searchResults;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage openHomePage() {
        driver.get(BASE_URL);
        return this;
    }

    public HomePage maximizeHomePage() {
        maximizeWindow();
        return this;
    }

    public HomePage checkTabName(String expectedTabName) {
        checkPageTabName(expectedTabName);
        return this;
    }

    public HomePage enterRandomDigits() {
        SecureRandom rnd = new SecureRandom();
        int length = 2 + rnd.nextInt(3);
        StringBuilder digits = new StringBuilder();

        for (int i = 0; i < length; i++) {
            digits.append(rnd.nextInt(10));
        }

        searchInput.clear();
        searchInput.sendKeys(digits.toString());
        return this;
    }

    public ResultPage selectResultFromSearchList(int index) {
        waitForVisibilityListOfElements(searchResults);
        if (searchResults.isEmpty()) {
            throw new IllegalStateException("No search results");
        }
        if (index < 1 || index > searchResults.size()) {
            throw new IllegalArgumentException("Invalid index: " + index);
        }
        searchResults.get(index - 1).click();
        return new ResultPage(driver);
    }
}
