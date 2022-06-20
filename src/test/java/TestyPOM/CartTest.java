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
    private String[] productPages = {"/egipt-el-gouna/", "/wspinaczka-via-ferraty/", "/wspinaczka-island-peak/",
            "/fuerteventura-sotavento/", "/grecja-limnos/", "/windsurfing-w-karpathos/",
            "/wyspy-zielonego-przyladka-sal/", "/wakacje-z-yoga-w-kraju-kwitnacej-wisni/",
            "/wczasy-relaksacyjne-z-yoga-w-toskanii/", "/yoga-i-pilates-w-hiszpanii/"};

    @Test
    public void addToCartFromProductPageTest() {
        ProductPage productPage = new ProductPage(driver).goTo(productUrl);
        productPage.notice.closeDemoNotice();
        boolean isProductInCart = productPage.addToCart().viewCart().isProductInCart(productId);
        assertTrue(isProductInCart,
                "Remove button was not found for a product with " + productId + " (Egipt - El Gouna). " +
                        "Was the product added to cart?");
    }

    @Test
    public void addToCartFromCategoryPageTest() {
        CategoryPage categoryPage = new CategoryPage(driver).goTo(productCategoryUrl);
        categoryPage.notice.closeDemoNotice();
        boolean isProductInCart = categoryPage.addToCart(productId).viewCart().isProductInCart(productId);

        assertTrue(isProductInCart,
                "Remove button was not found for a product with id=" + productId + " (Egipt - El Gouna). " +
                        "Was the product added to cart?");
    }

    @Test
    public void addOneProductTenTimesTest() {
        ProductPage productPage = new ProductPage(driver).goTo(productUrl);
        productPage.notice.closeDemoNotice();
        int quantity = productPage.addToCart(productQuantity).viewCart().getQuantityInt();
        assertEquals(10, quantity,
                "Quantity of the product is not what expected. Expected: 10, but was " + quantity);
    }

    @Test
    public void addTenProductsToCartTest() {
        ProductPage productPage = new ProductPage(driver);
        for (String product : productPages) {
            productPage.goTo("https://fakestore.testelka.pl/product" + product).addToCart();
        }
        int numberOfItems = productPage.header.viewCart().getNumberOfProducts();
        assertEquals(10, numberOfItems,
                "Number of items in the cart is not correct. Expected: 10, but was: " + numberOfItems);
    }

    @Test
    public void changeNumberOfProductsTest() {
        ProductPage productPage = new ProductPage(driver).goTo(productUrl);
        productPage.notice.closeDemoNotice();
        int quantity = productPage.addToCart("1").viewCart().setQuantity(8).updateCart().getQuantityOfProducts();
        assertEquals(8, quantity,
                "Quantity of the product is not what expected. Expected: 8, but was " + quantity);
    }

    @Test
    public void removePositionFromCartTest() {
        ProductPage productPage = new ProductPage(driver).goTo(productUrl);
        productPage.notice.closeDemoNotice();
        int numberOfEmptyCartMessages = productPage.addToCart("1").viewCart().removeFromCart(productId)
                .checkNumberOfEmptyCartMessages();
        assertEquals(1, numberOfEmptyCartMessages,
                "One message about empty cart was expected, but found " + numberOfEmptyCartMessages);
    }
}

