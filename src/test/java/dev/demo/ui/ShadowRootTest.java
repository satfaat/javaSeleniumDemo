package dev.demo.ui;

import dev.demo.base.BaseTest;
import dev.demo.data.reference.Urls;
import dev.demo.pages.ShadowDomPage;
import org.junit.jupiter.api.Test;

public class ShadowRootTest extends BaseTest {

    @Test
    public void testShadowRoot() throws InterruptedException {
        navigateTo(Urls.LUFTHANSA.getPath());
        ShadowDomPage shadowDomPage = new ShadowDomPage();

        Thread.sleep(3000);
        shadowDomPage.privacyAgree();
        shadowDomPage.clickShadowDomButton();
    }
}
