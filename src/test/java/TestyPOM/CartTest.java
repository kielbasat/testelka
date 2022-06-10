package TestyPOM;

import PageObjects.CategoryPage;
import PageObjects.ProductPage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CartTest extends BaseTest {

    private String productId = "386";
    private String productUrl = "https://fakestore.testelka.pl/product/egipt-el-gouna/";
    private String productCategoryUrl = "https://fakestore.testelka.pl/product-category/windsurfing/";
    private String productQuantity = "10";

    @Test
    public void addToCartFromProductPageTest() {
        ProductPage productPage = new ProductPage(driver);
        int productAmount = productPage.goTo(productUrl).addToCart().viewCart().getProductAmount(productId);
        assertTrue(productAmount == 1,
                "Remove button was not found for a product with " + productId + " (Egipt - El Gouna). " +
                        "Was the product added to cart?");
    }

    @Test
    public void addToCartFromCategoryPageTest() {
        CategoryPage categoryPage = new CategoryPage(driver);
        int productAmount = categoryPage.goTo(productCategoryUrl).addToCart(productId).viewCart().getProductAmount(productId);

        assertTrue(productAmount == 1,
                "Remove button was not found for a product with id=386 (Egipt - El Gouna). " +
                        "Was the product added to cart?");
    }

    @Test
    public void addOneProductTenTimesTest() {
        ProductPage productPage = new ProductPage(driver);
        int quantity = productPage.addProductToCart(productUrl, productQuantity).viewCart().getQuantityInt();
        assertEquals(10, quantity,
                "Quantity of the product is not what expected. Expected: 10, but was " + quantity);
    }
}

