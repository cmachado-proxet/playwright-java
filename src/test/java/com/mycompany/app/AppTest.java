package com.mycompany.app;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import com.microsoft.playwright.junit.Options;
import com.microsoft.playwright.junit.OptionsFactory;
import com.microsoft.playwright.junit.UsePlaywright;

@UsePlaywright(AppTest.CustomOptions.class)
class AppTest {

    public static class CustomOptions implements OptionsFactory {

        @Override
        public Options getOptions() {
            return new Options()
                    .setLaunchOptions(
                            new BrowserType.LaunchOptions().setHeadless(true).setSlowMo(100)
                    )
                    .setContextOptions(
                            new Browser.NewContextOptions().setRecordVideoDir(Paths.get("target/videos/"))
                    )
                    .setTrace(Options.Trace.ON);
        }
    }

    @Test
    void shouldGoToFacebookLink(Page page) {
        page.navigate("https://www.proxet.com/contact-us");

        Page newTab = page.waitForPopup(() -> {
            page.locator(".social-btns a[aria-label=\"Follow us on Facebook\"]").click();
        });
        newTab.waitForLoadState();
        assertEquals("https://www.facebook.com/Proxet", newTab.url());

        // page.pause();
    }

    @Test
    void shouldSubmitForm(Page page) {
        page.navigate("https://www.proxet.com/contact-us");
        page.locator("input[name=\"firstname\"]").click();
        page.locator("input[name=\"firstname\"]").fill("John");

        page.locator("input[name=\"lastname\"]").click();
        page.locator("input[name=\"lastname\"]").fill("Doe");

        page.locator("input[name=\"email\"]").click();
        page.locator("input[name=\"email\"]").fill("john@test.com");

        page.locator("input[name=\"phone\"]").click();
        page.locator("input[name=\"phone\"]").fill("987654321987654321987");

        page.locator("input[type=\"submit\"]").click();

        Locator element = page.locator(".submitted-message");

        assertThat(element).hasText("Thanks for submitting the form.");

        // page.pause();
    }

}
