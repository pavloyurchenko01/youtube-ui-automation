package tests;

import org.testng.annotations.Test;
import pages.HomePage;

public class YouTubeTest extends BaseTest {

    @Test(description = "Verify user can search random digits on YouTube, " +
            "open the second search result, " +
            "play the fourth video, " +
            "open channel page, " +
            "click 'Subscribe' and verify text on the button in the pop-up, " +
            "then close browser tab'")
    public void testSearch() throws InterruptedException {

        String pageTitle = "YouTube";
        String expectedText = "Увійти";

        new HomePage(driver)
                .openHomePage()
                .checkTabName(pageTitle)
                .enterRandomDigits()
                .maximizeHomePage()
                .selectResultFromSearchList(2)
                .selectVideoByIndex(4)
                .clickOnSenderAvatar()
                .clickOnSubscribeButton()
                .verifyLoginButtonTextInPopUp(expectedText)
                .closeBrowserTab();
    }
}
