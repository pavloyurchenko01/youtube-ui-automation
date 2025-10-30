package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ChannelPage extends BasePage {

    @FindBy(xpath = "//button[@aria-label='Підписатися']")
    private WebElement subscribe;

    @FindBy(xpath = "//*[contains(@class,'ytd-popup-container')]//*[@aria-label='Увійти']")
    private WebElement popUpSignInButton;

    public ChannelPage(WebDriver driver) {
        super(driver);
    }

    public ChannelPage clickOnSubscribeButton() {
        waitForVisibilityOfElement(subscribe);
        clickOnElement(subscribe);
        return this;
    }

    public ChannelPage verifyLoginButtonTextInPopUp(String expectedText) {
        waitForVisibilityOfElement(popUpSignInButton);
        String actualText = getAttributeValue(popUpSignInButton).trim();
        assertThat(actualText)
                .as("Check that element has expected text")
                .isEqualTo(expectedText);
        return this;
    }
}
