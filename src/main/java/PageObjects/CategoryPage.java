package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CategoryPage extends BasePage {

    private WebDriverWait wait;
    public NoticePage notice;

    public CategoryPage(WebDriver driver) {
        super(driver);
        notice = new NoticePage(driver);
        wait = new WebDriverWait(driver, 5);
    }

    private By viewCartButtonLocator = By.cssSelector(".added_to_cart");
    String addToCartButtonCssSelector = ".post-<product_id>>.add_to_cart_button";

    public CategoryPage goTo(String url) {

        driver.navigate().to(url);
        return new CategoryPage(driver);
    }

    public CategoryPage addToCart(String productId) {
        By AddToCartButton = By.cssSelector(addToCartButtonCssSelector.replace("<product_id>", productId));
        driver.findElement(AddToCartButton).click();
        wait.until(ExpectedConditions.attributeContains(AddToCartButton, "class", "added"));
        return new CategoryPage(driver);
    }

    public CartPage viewCart() {
        wait.until(ExpectedConditions.elementToBeClickable(viewCartButtonLocator)).click();
        return new CartPage(driver);
    }
}
