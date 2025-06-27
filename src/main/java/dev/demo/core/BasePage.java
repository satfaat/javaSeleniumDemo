package dev.demo.core;

import dev.demo.core.driver.DriverManager;
import dev.demo.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage() {
        this.driver = DriverManager.getDriver();
        this.wait = WaitUtils.createWait(driver);
        PageFactory.initElements(driver, this);
    }

    protected WebElement waitForElementToBeVisible(WebElement webElement) {
        return WaitUtils.waitForElementToBeVisible(wait, webElement);
    }

    protected WebElement waitForElementToBeClickable(WebElement webElement) {
        return WaitUtils.waitForElementToBeClickable(wait, webElement);
    }

    protected WebElement fluentWait(By locator) {
        return WaitUtils.fluentWait(driver, locator);
    }

    protected void waitForPageLoad() {
        WaitUtils.waitForPageLoad(driver);
    }

    protected void navigateTo(String url) {
        driver.get(url);
    }

    protected WebElement findElement(By by) {
        return driver.findElement(by);
    }
}
