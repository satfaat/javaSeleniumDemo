package dev.demo.ui;

import dev.demo.base.BaseTest;
import dev.demo.config.ConfTest;
import dev.demo.config.FrameworkConstants;
import dev.demo.pages.LoginPage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class LoginTest extends BaseTest {

    @Test
    void successfulLoginTest() {
        navigateTo(FrameworkConstants.BASE_URL);
        LoginPage loginPage = new LoginPage(driver);

        assertTrue(loginPage.isLoginPageDisplayed());
        loginPage
                .inputUsername(ConfTest.secretProperties.readValue("username1"))
                .inputPassword(ConfTest.secretProperties.readValue("pw"))
                .clickBtnLogin();

        // Add assertions for successful login
    }

    @Test
    void invalidLoginTest() {
        navigateTo(FrameworkConstants.BASE_URL);
        LoginPage loginPage = new LoginPage(driver);

        assertTrue(loginPage.isLoginPageDisplayed());
        loginPage
                .inputUsername("invalid")
                .inputPassword("credentials")
                .clickBtnLogin();

        // Add assertions for error message
    }
}
