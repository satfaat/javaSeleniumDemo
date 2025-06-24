package dev.demo.pages;

import dev.demo.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(id = "user-name")
    private WebElement FieldUsername;

    @FindBy(id = "password")
    private WebElement fieldPassword;

    @FindBy(id = "login-button")
    private WebElement buttonLogin;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage inputUsername(String username) {
        waitForElementToBeVisible(FieldUsername).clear();
        FieldUsername.sendKeys(username);
        return this;
    }

    public LoginPage inputPassword(String password) {
        waitForElementToBeVisible(fieldPassword).clear();
        fieldPassword.sendKeys(password);
        return this;
    }

    public LoginPage clickBtnLogin() {
        waitForElementToBeClickable(buttonLogin).click();
        return this;
    }

    public boolean isLoginPageDisplayed() {
        return FieldUsername.isDisplayed() && fieldPassword.isDisplayed();
    }
}
