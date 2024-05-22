package org.example.features.login;

import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.example.steps.serenity.EndUserSteps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

@RunWith(SerenityParameterizedRunner.class)
@UseTestDataFrom("login-params.csv")
public class LoginTest {
    @Managed(uniqueSession = true)
    public WebDriver webdriver;

    @Steps
    public EndUserSteps userSteps;

    private String username;
    private String password;
    private String expectedMessage;
    private Boolean valid;

    @Test
    public void login_with_valid_and_non_valid_params() {
        System.out.println(username + " " + password + " " + expectedMessage + " " + valid);
        userSteps.opens_home_page();
        userSteps.logs_in(username, password);
        if (valid) {
            userSteps.should_see_welcome_message(expectedMessage);
        } else {
            userSteps.should_see_alert_message(expectedMessage);
        }
    }
}
