package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage extends BasePage {

    public HeaderPage header;
    public NoticePage notice;
    private WebDriverWait wait;

    public ProductPage(WebDriver driver) {
        super(driver);
        header = new HeaderPage(driver);
        notice = new NoticePage(driver);
        wait = new WebDriverWait(driver, 7);
    }

    private By addToCartButtonLocator = By.cssSelector("button[name='add-to-cart']");
    private By viewCartButtonLocator = By.cssSelector(".woocommerce-message>.button");
    private By quantityFieldLocator = By.cssSelector("input.qty");

    public ProductPage goTo(String productUrl) {
        driver.navigate().to(productUrl);
        return new ProductPage(driver);
    }

    public ProductPage addToCart() {
        WebElement addToCartButton = driver.findElement(this.addToCartButtonLocator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addToCartButton);
        addToCartButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(viewCartButtonLocator));
        return new ProductPage(driver);
    }

    public CartPage viewCart() {
        wait.until(ExpectedConditions.elementToBeClickable(viewCartButtonLocator)).click();
        return new CartPage(driver);
    }

    public ProductPage addToCart(String quantity) {
        WebElement quantityField = driver.findElement(quantityFieldLocator);
        quantityField.clear();
        quantityField.sendKeys(quantity);
        addToCart();
        return new ProductPage(driver);
    }
}
