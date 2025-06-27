package dev.demo.base;

import dev.demo.core.driver.DriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ExtendWith(TestWatcherExtension.class)
public abstract class BaseTest {

    // Logger is now static and final
    private static final Logger logger = LoggerFactory.getLogger(BaseTest.class);

    @BeforeEach
    public void setUp(TestInfo testInfo) {
        DriverManager.getDriver();
        logger.info("Starting test: {}", testInfo.getDisplayName());
        ;
    }

    @AfterEach
    public void tearDown(TestInfo testInfo) {
        DriverManager.quitDriver(testInfo);
        logger.info("Finished test: {}", testInfo.getDisplayName());
    }

    protected void navigateTo(String url) {
        DriverManager.getDriver().get(url);
    }
}
