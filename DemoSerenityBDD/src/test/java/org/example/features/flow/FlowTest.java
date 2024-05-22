package org.example.features.flow;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.example.steps.serenity.EndUserSteps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

@RunWith(SerenityRunner.class)
public class FlowTest {

    @Managed(uniqueSession = true)
    public WebDriver webdriver;

    @Steps
    public EndUserSteps userSteps;

    @Test
    public void complete_purchase_flow() {
        userSteps.opens_home_page();
        userSteps.logs_in("asdas1", "asdas1");
        userSteps.should_see_welcome_message("Welcome asdas1");
        userSteps.search_and_add_product_to_cart();
        userSteps.should_see_alert_message("Product added");
        userSteps.place_order("John Doe", "USA", "New York", "1234567890123456", "12", "2024");
        userSteps.should_see_thank_you_message();
        userSteps.logs_out();
        userSteps.should_see_login_button();
    }
}