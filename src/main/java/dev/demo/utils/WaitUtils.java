package dev.demo.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import dev.demo.config.FrameworkConstants;

import java.time.Duration;

public class WaitUtils {
    private static final long DEFAULT_TIMEOUT_SECONDS = 20L;

    private WaitUtils() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Creates a WebDriverWait instance with a custom timeout.
     *
     * @param driver         The WebDriver instance.
     * @param timeoutSeconds The timeout in seconds.
     * @return A configured WebDriverWait instance.
     */
    public static WebDriverWait createWait(WebDriver driver, long timeoutSeconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
    }

    /**
     * Creates a WebDriverWait instance with the default timeout.
     *
     * @param driver The WebDriver instance.
     * @return A configured WebDriverWait instance.
     */
    public static WebDriverWait createWait(WebDriver driver) {
        return createWait(driver, DEFAULT_TIMEOUT_SECONDS);
    }

    public static WebElement waitForElementVisible(WebDriver driver, By locator) {
        return new WebDriverWait(
                driver,
                Duration.ofSeconds(FrameworkConstants.EXPLICIT_WAIT)
        ).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement waitForElementToBeVisible(WebDriverWait wait, WebElement webElement) {
        System.out.println("Waiting for element to be visible: " + webElement);
        try {
            return wait.until(ExpectedConditions.visibilityOf(webElement));
        } catch (Exception e) {
            System.out.println("Element not visible after waiting: " + webElement);
            throw e;
        }
    }

    public static WebElement waitForElementToBeClickable(WebDriverWait wait, WebElement webElement) {
        System.out.println("Waiting for element to be clickable: " + webElement);
        try {
            return wait.until(ExpectedConditions.elementToBeClickable(webElement));
        } catch (Exception e) {
            System.out.println("Element not clickable after waiting: " + webElement);
            throw e;
        }
    }

    public static WebElement waitForElementToBePresent(WebDriverWait wait, By by) {
        System.out.println("Waiting for element to be present: " + by);
        try {
            return wait.until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (Exception e) {
            System.out.println("Element " + by + " was not present within the wait duration.");
            throw e;
        }
    }

    public static WebElement fluentWait(WebDriver driver, By locator) {
        return new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(FrameworkConstants.EXPLICIT_WAIT))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(Exception.class)
                .until(d -> d.findElement(locator));
    }

    public static void waitForPageLoad(WebDriver driver) {
        new WebDriverWait(driver, Duration.ofSeconds(FrameworkConstants.PAGE_LOAD_TIMEOUT))
                .until(d -> String.valueOf(((JavascriptExecutor) d).executeScript("return document.readyState"))
                        .equals("complete"));
    }
}
