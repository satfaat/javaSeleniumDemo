package dev.demo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    @FindBy(id = "user-name")
    private WebElement FieldUsername;

    @FindBy(id = "password")
    private WebElement fieldPassword;

    @FindBy(id = "login-button")
    private WebElement buttonLogin;

    public LoginPage(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public LoginPage inputUsername(String username) {
        FieldUsername.clear();
        FieldUsername.sendKeys(username);
        return this;
    }

    public LoginPage inputPassword(String password) {
        fieldPassword.clear();
        fieldPassword.sendKeys(password);
        return this;
    }

    public LoginPage clickBtnLogin() {
        buttonLogin.click();
        return this;
    }

    public boolean isLoginPageDisplayed() {
        return FieldUsername.isDisplayed() && fieldPassword.isDisplayed();
    }
}
