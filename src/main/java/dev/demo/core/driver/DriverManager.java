package dev.demo.core.driver;

import dev.demo.utils.ScreenshotUtils;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.WebDriver;

public class DriverManager {
    // A ThreadLocal variable to store the WebDriver instance for each thread.
    private static final ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();
    private static final ThreadLocal<Boolean> testHasFailed = ThreadLocal.withInitial(() -> false);

    private DriverManager() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Gets the WebDriver instance for the current thread.
     * If one doesn't exist, it creates a new one using the DriverFactory.
     *
     * @return The thread-safe WebDriver instance.
     */
    public static WebDriver getDriver() {
        if (threadLocalDriver.get() == null) {
            threadLocalDriver.set(DriverFactory.createDriver());
        }
        return threadLocalDriver.get();
    }

    /**
     * Quits the driver, but first takes a screenshot if the test was marked as failed.
     * This method also cleans up all ThreadLocal variables for the current thread.
     */
    public static void quitDriver(TestInfo testInfo) {
        WebDriver driver = threadLocalDriver.get();
        if (driver != null) {
            // Check the flag before taking a screenshot
            if (testHasFailed.get()) {
                String testName = testInfo.getDisplayName().replaceAll("[^a-zA-Z0-9.-]", "_");
                ScreenshotUtils.takeScreenshot(driver, testName);
            }

            driver.quit();
            threadLocalDriver.remove();
            testHasFailed.remove(); // Important: Clean up the failure flag
        }
    }

    /**
     * Sets the status of the current test thread to 'failed'.
     * This will be called by the TestWatcher.
     */
    public static void setTestAsFailed() {
        testHasFailed.set(true);
    }

}
