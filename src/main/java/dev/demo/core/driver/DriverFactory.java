package dev.demo.core.driver;

import dev.demo.config.FrameworkConstants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

import static dev.demo.config.FrameworkConstants.*;

/**
 * A factory class for creating and configuring WebDriver instances.
 * This class is not meant to be instantiated.
 */
public final class DriverFactory {
    private DriverFactory() {
        throw new IllegalStateException("Utility class");
    }

    public static WebDriver createDriver() {
        // Read browser from system property, default to CHROME
        String browserName = System.getProperty("browser", "chrome");
        Browser browser;
        try {
            browser = Browser.valueOf(browserName.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Unsupported browser specified: " + browserName);
        }
        boolean headless = Boolean.parseBoolean(System.getProperty("headless", "false"));
        WebDriver driver;

        switch (browser) {
            case CHROME:
                ChromeOptions chromeOptions = new ChromeOptions();
                if (headless) chromeOptions.addArguments("--headless=new");
                chromeOptions.addArguments("--start-maximized");
                chromeOptions.addArguments("--remote-allow-origins=*");
                driver = new ChromeDriver(chromeOptions);
                break;

            case FIREFOX:
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if (headless) firefoxOptions.addArguments("--headless");
                driver = new FirefoxDriver(firefoxOptions);
                break;

            case EDGE:
                EdgeOptions edgeOptions = new EdgeOptions();
                if (headless) edgeOptions.addArguments("--headless");
                edgeOptions.addArguments("--start-maximized");
                driver = new EdgeDriver(edgeOptions);
                break;

            default:
                throw new IllegalStateException("Browser enum was valid but not handled in switch: " + browser);
        }
        driver.manage().timeouts().implicitlyWait(getDuration(IMPLICIT_WAIT));
        driver.manage().timeouts().pageLoadTimeout(getDuration(PAGE_LOAD_TIMEOUT));

        if (browser == Browser.FIREFOX && !headless) {
            driver.manage().window().maximize();
        }
        return driver;
    }
}
