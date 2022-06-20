package TestyPOM;

import PageObjects.OrderReceivedPage;
import PageObjects.ProductPage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PaymentTest extends BaseTest {

    private String productUrl = "https://fakestore.testelka.pl/product/egipt-el-gouna/";

    @Test
    public void buyWithoutAccountTest() {
        ProductPage productPage = new ProductPage(driver).goTo(productUrl);
        productPage.notice.closeDemoNotice();
        productPage.addToCart().viewCart().checkout().
                fillOutCheckoutForm("test@test.pl", "888777666").fillOutCardData(true).order();
        OrderReceivedPage orderReceivedPage = new OrderReceivedPage(driver);
        boolean isOrderSuccessful = orderReceivedPage.isOrderSucessful();
        assertTrue(isOrderSuccessful,
                "Number of 'order received' messages is not 1. Was the payment successful?");
    }


}
