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
    public static WebElement waitForElementVisible(WebDriver driver, By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(FrameworkConstants.EXPLICIT_WAIT))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
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
