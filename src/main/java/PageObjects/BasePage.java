package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public abstract class BasePage {

    protected WebDriver driver;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
    }

    private By demoNoticeLocator = By.cssSelector(".woocommerce-store-notice__dismiss-link");

    public void closeDemoNotice() {
        driver.findElement(demoNoticeLocator).click();
    }

}
