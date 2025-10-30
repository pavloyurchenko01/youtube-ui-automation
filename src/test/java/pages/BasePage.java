package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public abstract class BasePage {

    private static final int DEFAULT_TIMEOUT = 10;
    protected static final String BASE_URL =
            System.getProperty("baseUrl", "https://www.youtube.com/");

    protected WebDriver driver;
    protected WebDriverWait wait;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
        PageFactory.initElements(driver, this);
    }

    protected void waitForVisibilityOfElement(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitForVisibilityListOfElements(List<WebElement> elements) {
        wait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    protected void waitUntilElementIsClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void clickOnElement(WebElement element) {
        waitUntilElementIsClickable(element);
        element.click();
    }

    protected String getAttributeValue(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        return element.getAttribute("aria-label");
    }

    protected void checkPageTabName(String expectedTitle) {
        String actualTitle = driver.getTitle();
        assertThat(actualTitle)
                .as("Check page tab name")
                .isEqualTo(expectedTitle);
    }

    protected void clickAndVerify(WebElement element) {

    }

    protected void maximizeWindow() {
        try {
            driver.manage().window().maximize();
        } catch (Exception ignored) {
        }
    }

    public void closeBrowserTab() {
        driver.close();
    }

    protected void waitForListVisible(List<WebElement> elements) {
        wait.until(driver -> {
            try {
                return elements != null && !elements.isEmpty();
            } catch (StaleElementReferenceException e) {
                return false;
            }
        });
    }

    protected void clickOnElementByIndex(List<WebElement> elements, int index) {
        waitForListVisible(elements);

        if (index < 1 || index > elements.size()) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        WebElement target = elements.get(index - 1);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        int maxScroll = 10;
        int scrollStep = 500;

        for (int i = 0; i < maxScroll; i++) {
            try {
                if (target.isDisplayed()) {
                    js.executeScript("arguments[0].scrollIntoView({block:'center'});", target);
                    wait.until(ExpectedConditions.elementToBeClickable(target)).click();
                    return;
                }
            } catch (StaleElementReferenceException ignored) {
                waitForListVisible(elements);
                target = elements.get(index - 1);
            }
            js.executeScript("window.scrollBy(0, arguments[0]);", scrollStep);
        }
    }
}
