package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage extends BasePage {

    public CartPage(WebDriver driver) {
        super(driver);
    }
    private WebDriverWait wait = new WebDriverWait(driver, 7);
    private By shopTableLocator = By.cssSelector(".shop_table");
    private By productQuantityLocator = By.cssSelector("div.quantity>input");
    private By cartItemLocator = By.cssSelector(".cart_item");
    private By cartQuantityFieldLocator = By.cssSelector("input.qty");
    private By updateCartButtonLocator = By.cssSelector("[name='update_cart']");
    private By quantityOfProductLocator = By.cssSelector("div.quantity>input");
    private By blockOverlayLocator = By.cssSelector(".blockOverlay");
    private By emptyCartMessageLocator = By.cssSelector("p.cart-empty");
    private String removeProductCssSelector = "a[data-product_id='<product_id>']";

    public int getQuantityInt() {

        waitForShopTable();
        String quantityString = driver.findElement(productQuantityLocator).getAttribute("value");
        int quantityInt = Integer.parseInt(quantityString);
        return quantityInt;
    }

    public boolean isProductInCart(String productId) {
        waitForShopTable();
        By removeProductLocator = By.cssSelector(removeProductCssSelector.replace("<product_id>", productId));
        int productRecords = driver.findElements(removeProductLocator).size();
        boolean presenceOfProduct = false;
        if (productRecords == 1) {
            presenceOfProduct = true;
        } else if (productRecords > 1){
            throw new IllegalArgumentException("There is more than one record for product in cart.");
        }
        return presenceOfProduct;
    }

    public int getNumberOfProducts() {
        waitForShopTable();
        return driver.findElements(cartItemLocator).size();
    }

     private void waitForShopTable() {
         wait.until(ExpectedConditions.presenceOfElementLocated(shopTableLocator));
     }


    public CartPage setQuantity() {
        WebElement quantityField = driver.findElement(cartQuantityFieldLocator);
        quantityField.clear();
        quantityField.sendKeys("8");
        return new CartPage(driver);
    }

    public CartPage updateCart() {
        WebElement updateButton = driver.findElement(updateCartButtonLocator);
        wait.until(ExpectedConditions.elementToBeClickable(updateButton));
        updateButton.click();
        return new CartPage(driver);
    }

    public int getQuantityOfProduct() {
        String quantityString = driver.findElement(quantityOfProductLocator).getAttribute("value");
        int quantity = Integer.parseInt(quantityString);
        return quantity;
    }

    public CartPage removeFromCart(String productId) {
        By removeProductLocator = By.cssSelector(removeProductCssSelector.replace("<product_id>",productId));
        driver.findElement(removeProductLocator).click();
        return new CartPage(driver);
    }

    public int checkNumberOfEmptyCartMessages() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(blockOverlayLocator));
        return driver.findElements(emptyCartMessageLocator).size();
    }
}

