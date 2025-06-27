package dev.demo.pages;

import dev.demo.core.BasePage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ShadowDomPage extends BasePage {

    @FindBy(css = "button#cm-acceptAll")
    private WebElement btnAgree;

    private static final String SHADOW_DOM_BUTTON_SCRIPT = "return document.querySelector('maui-modal').shadowRoot" +
            ".querySelector('div.panel > .header-structure > .header-actions > maui-icon-link').shadowRoot" +
            ".querySelector('button')";

    public ShadowDomPage() {
        super();
    }


    public void privacyAgree() {
        waitForElementToBeClickable(btnAgree).click();
    }

    /**
     * Retrieves a WebElement located within a shadow DOM using a JavaScript query.
     * This method waits for the element to be present and displayed in the DOM.
     *
     * @return The WebElement found within the shadow DOM, or null if not found within the timeout.
     */
    private WebElement getShadowDomButtonElement() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        try {
            return new WebDriverWait(driver, Duration.ofSeconds(20)) // Use a reasonable timeout
                    .until(d -> {
                        WebElement element = (WebElement) js.executeScript(SHADOW_DOM_BUTTON_SCRIPT);
                        // Return the element only if it's not null and is displayed
                        return element != null && element.isDisplayed() ? element : null;
                    });
        } catch (TimeoutException e) {
            // Log the timeout and return null or throw a more specific exception
            System.err.println("Timeout waiting for shadow DOM button: " + SHADOW_DOM_BUTTON_SCRIPT + ". Error: " + e.getMessage());
            return null;
        }
    }

    /**
     * Clicks a button located within a shadow DOM.
     * This method first finds the element using JavaScript and then clicks it.
     * It handles cases where the element might not be immediately available.
     *
     * @throws org.openqa.selenium.NoSuchElementException if the shadow DOM button is not found or not clickable.
     */
    public void clickShadowDomButton() {
        WebElement shadowButton = getShadowDomButtonElement();
        if (shadowButton != null) {
            // For shadow DOM elements retrieved via JS, direct click is often the approach.
            // If further waiting for clickability is needed, you might need a custom JS-based wait.
            shadowButton.click();
        } else {
            throw new org.openqa.selenium.NoSuchElementException("Shadow DOM button not found or not clickable.");
        }
    }
}
