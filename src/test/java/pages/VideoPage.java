package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class VideoPage extends BasePage {

    @FindBy(xpath = "//*[@class='style-scope ytd-watch-metadata']//*[@id='avatar']")
    private WebElement videoOwner;

    @FindBy(xpath = "//div[@id='text-container']//*[contains(@class,'yt-simple-endpoint style-scope yt-formatted-string')]")
    private WebElement channelName;

    public VideoPage(WebDriver driver) {
        super(driver);
    }

    public ChannelPage clickOnSenderAvatar() {
        clickOnElement(videoOwner);
        return new ChannelPage(driver);
    }

}
