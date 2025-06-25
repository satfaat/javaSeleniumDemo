package dev.demo.core.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactory {
    private DriverFactory() {
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

        switch (browser) {
            case CHROME:
                ChromeOptions chromeOptions = new ChromeOptions();
                if (headless) chromeOptions.addArguments("--headless=new");
                chromeOptions.addArguments("--start-maximized");
                chromeOptions.addArguments("--remote-allow-origins=*");
                return new ChromeDriver(chromeOptions);

            case FIREFOX:
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if (headless) firefoxOptions.addArguments("--headless");
                return new FirefoxDriver(firefoxOptions);

            case EDGE:
                EdgeOptions edgeOptions = new EdgeOptions();
                if (headless) edgeOptions.addArguments("--headless");
                edgeOptions.addArguments("--start-maximized");
                return new EdgeDriver(edgeOptions);
        }
        throw new IllegalStateException("Browser enum was valid but not handled in switch: " + browser);
    }
}
