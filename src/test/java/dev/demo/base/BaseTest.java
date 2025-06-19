package dev.demo.base;

import dev.demo.core.driver.DriverFactory;
import dev.demo.utils.ScreenshotUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static dev.demo.config.FrameworkConstants.*;

public class BaseTest {

    protected final Logger logger = LoggerFactory.getLogger(getClass());
    protected WebDriver driver;

    @BeforeEach
    public void setUp(TestInfo testInfo) {
        driver = DriverFactory.createDriver();
        logger.info("Starting test: {}", testInfo.getDisplayName());
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(getDuration(IMPLICIT_WAIT));
        driver.manage().timeouts().pageLoadTimeout(getDuration(PAGE_LOAD_TIMEOUT));
    }

    @AfterEach
    public void tearDown(TestInfo testInfo) {
        if (driver != null) {
            ScreenshotUtils.takeScreenshot(driver, testInfo.getDisplayName());
            driver.quit();
            driver = null;
            logger.info("Finished test: {}", testInfo.getDisplayName());
        }
    }

    protected void navigateTo(String url) {
        driver.get(url);
    }
}
