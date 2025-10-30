package util;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

    public static WebDriver createDriver(String browserName) {
        if (browserName == null || browserName.isEmpty()) {
            browserName = "chrome";
        }

        return switch (browserName.toLowerCase()) {
            case "firefox" -> {
                WebDriverManager.firefoxdriver().setup();
                yield new FirefoxDriver();
            }
            case "edge" -> {
                WebDriverManager.edgedriver().setup();
                yield new EdgeDriver();
            }
            default -> {
                WebDriverManager.chromedriver().setup();
                yield new ChromeDriver();
            }
        };
    }
}
