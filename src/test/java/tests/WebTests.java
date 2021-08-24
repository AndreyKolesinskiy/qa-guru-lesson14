package tests;

import com.codeborne.selenide.Configuration;
import config.ApiConfig;
import config.WebConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class WebTests {

    @BeforeAll
    public static void setUp() {
        WebConfig config = ConfigFactory.create(WebConfig.class, System.getProperties());
        Configuration.browser = config.browser();
        Configuration.browserVersion = config.browserVersion();

        DesiredCapabilities capabilities = new DesiredCapabilities();

        if (!config.remoteDriverUrl().equals("")) {
            capabilities.setCapability("enableVNC", true);
            capabilities.setCapability("enableVideo", true);
            Configuration.remote = config.remoteDriverUrl();
        }

        Configuration.browserCapabilities = capabilities;
    }

    @Test
    public void dragRectangleAtoPlaceB() {
        open("https://the-internet.herokuapp.com/drag_and_drop");
        $("#column-a").dragAndDropTo("#column-b");
        $("#column-a").shouldHave(text("B"));
        $("#column-b").shouldHave(text("A"));
    }
}
