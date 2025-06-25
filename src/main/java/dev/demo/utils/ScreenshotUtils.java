package dev.demo.utils;

import dev.demo.config.FrameworkConstants;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotUtils {
    private static final DateTimeFormatter TIMESTAMP_FORMATTER =
            DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");

    private ScreenshotUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static void takeScreenshot(WebDriver driver, String testName) {
        if (driver == null) {
            System.err.println("WebDriver is null. Cannot take screenshot for test: " + testName);
            return;
        }

        try {
            // 1. Define the destination directory using the Path API
            Path screenshotDir = Paths.get(FrameworkConstants.SCREENSHOT_PATH);

            // 2. Ensure the directory exists. If not, create it.
            Files.createDirectories(screenshotDir);

            // 3. Capture the screenshot as a file
            Path srcPath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE).toPath();

            // 4. Generate a unique filename using the modern date/time API
            String timestamp = LocalDateTime.now().format(TIMESTAMP_FORMATTER);
            String fileName = testName + "_" + timestamp + ".png";
            Path destPath = screenshotDir.resolve(fileName);

            Files.copy(srcPath, destPath, StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException e) {
            System.err.println("Failed to capture or save screenshot: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            // Catch other potential exceptions from WebDriver or file system
            System.err.println("An unexpected error occurred while taking a screenshot: " + e.getMessage());
        }
    }
}
