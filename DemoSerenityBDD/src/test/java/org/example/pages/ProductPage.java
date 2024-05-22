package org.example.pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.annotations.findby.FindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage extends PageObject {

    @FindBy(linkText = "Add to cart")
    private WebElement addToCartButton;

    @FindBy(id = "cartur")
    private WebElement cartButton;

    public void clickAddToCart() {
        addToCartButton.click();
    }

    public void clickCartButton() {
        cartButton.click();
    }

    public void waitForAlertAndAccept() {
        WebDriverWait wait = new WebDriverWait(getDriver(), 10);
        wait.until(ExpectedConditions.alertIsPresent());
        getDriver().switchTo().alert().accept();
    }
}
