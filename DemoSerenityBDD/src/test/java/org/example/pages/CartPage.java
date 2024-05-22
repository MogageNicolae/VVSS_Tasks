package org.example.pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.annotations.findby.FindBy;
import org.openqa.selenium.WebElement;

public class CartPage extends PageObject {

    @FindBy(xpath = "//button[text()='Place Order']")
    private WebElement placeOrderButton;

    @FindBy(id = "name")
    private WebElement nameField;

    @FindBy(id = "country")
    private WebElement countryField;

    @FindBy(id = "city")
    private WebElement cityField;

    @FindBy(id = "card")
    private WebElement cardField;

    @FindBy(id = "month")
    private WebElement monthField;

    @FindBy(id = "year")
    private WebElement yearField;

    @FindBy(xpath = "//button[text()='Purchase']")
    private WebElement purchaseButton;

    @FindBy(xpath = "//h2[text()='Thank you for your purchase!']")
    private WebElement thankYouMessage;

    @FindBy(xpath = "//button[text()='OK']")
    private WebElement okButton;

    @FindBy(id = "logout2")
    private WebElement logoutButton;

    public void clickPlaceOrder() {
        placeOrderButton.click();
    }

    public void fillOrderForm(String name, String country, String city, String card, String month, String year) {
        nameField.sendKeys(name);
        countryField.sendKeys(country);
        cityField.sendKeys(city);
        cardField.sendKeys(card);
        monthField.sendKeys(month);
        yearField.sendKeys(year);
    }

    public void clickPurchaseButton() {
        purchaseButton.click();
    }

    public void verifyThankYouMessage() {
        thankYouMessage.isDisplayed();
    }

    public void clickOkButton() {
        okButton.click();
    }

    public void clickLogoutButton() {
        logoutButton.click();
    }
}
