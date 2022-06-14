package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HeaderPage extends BasePage{
    protected HeaderPage(WebDriver driver) {
        super(driver);
    }

private By TotalPriceLocator = By.cssSelector(".cart-contents");

    public CartPage viewCart() {
        driver.findElement(TotalPriceLocator).click();
        return new CartPage(driver);
    }
}
