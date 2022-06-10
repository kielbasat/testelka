package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage extends BasePage {

    public CartPage(WebDriver driver) {
        super(driver);
    }

    By shopTableLocator = By.cssSelector(".shop_table");
    By productQuantityLocator = By.cssSelector("div.quantity>input");
    String removeProductCssSelector = "a[data-product_id='<product_id>']";

    public int getProductAmount(String productId) {
        WebDriverWait wait = new WebDriverWait(driver, 7);
        wait.until(ExpectedConditions.presenceOfElementLocated(shopTableLocator));
        By removeProductLocator = By.cssSelector(removeProductCssSelector.replace("<product_id>", productId));
        return driver.findElements(removeProductLocator).size();
    }

    public int getQuantityInt() {
        String quantityString = driver.findElement(productQuantityLocator).getAttribute("value");
        int quantityInt = Integer.parseInt(quantityString);
        return quantityInt;
    }
}

