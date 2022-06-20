package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderReceivedPage extends BasePage{

    WebDriverWait wait;

    public OrderReceivedPage(WebDriver driver){
        super(driver);
        wait = new WebDriverWait(driver, 20);
    }

    private By successOrderMessageLocator = By.cssSelector(".woocommerce-thankyou-order-received");

    public boolean isOrderSucessful() {
       wait.until(ExpectedConditions.presenceOfElementLocated(successOrderMessageLocator));
       int numberOfSuccessfullorders = driver.findElements(successOrderMessageLocator).size();
       if (numberOfSuccessfullorders == 1) {
           return true;
       } else if (numberOfSuccessfullorders == 0){
           return false;
       } else {
           throw new IllegalArgumentException("Number of success messeges is: " + numberOfSuccessfullorders);
       }

    }
}
