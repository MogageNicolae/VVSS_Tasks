package org.example.steps.serenity;

import org.example.pages.CartPage;
import net.thucydides.core.annotations.Step;
import org.example.pages.LoginPage;
import org.example.pages.ProductPage;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static net.thucydides.core.webdriver.ThucydidesWebDriverSupport.getDriver;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasItem;

public class EndUserSteps {
    private LoginPage loginPage;
    private ProductPage productPage;
    private CartPage cartPage;

    @Step
    public void opens_home_page() {
        loginPage.open();
    }

    @Step
    public void logs_in(String username, String password) {
        loginPage.clickLogin();
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickSubmitButton();
    }

    @Step
    public void waitForSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Step
    public void should_see_welcome_message(String expectedMessage) {
        waitForSeconds(3);
        assertThat(loginPage.getWelcomeMessage(), containsString(expectedMessage));
    }

    @Step
    public void should_see_alert_message(String expectedMessage) {
        WebDriverWait wait = new WebDriverWait(getDriver(), 10);
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String alertText = alert.getText();
        assertThat(alertText, containsString(expectedMessage));
        alert.accept();
    }

    @Step
    public void search_and_add_product_to_cart() {
        waitForSeconds(1);
        productPage.find(By.linkText("Samsung galaxy s6")).click();
        productPage.clickAddToCart();
    }

    @Step
    public void place_order(String name, String country, String city, String card, String month, String year) {
        productPage.clickCartButton();
        cartPage.clickPlaceOrder();
        cartPage.fillOrderForm(name, country, city, card, month, year);
        cartPage.clickPurchaseButton();
        cartPage.verifyThankYouMessage();
        waitForSeconds(1);
    }

    @Step
    public void should_see_thank_you_message() {
        cartPage.verifyThankYouMessage();
    }

    @Step
    public void logs_out() {
        cartPage.clickOkButton();
        cartPage.find(By.id("logout2")).click();
    }

    @Step
    public void should_see_login_button() {
        loginPage.find(By.id("login2")).isDisplayed();
    }
}