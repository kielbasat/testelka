package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CheckoutPage extends BasePage {

    public CheckoutPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, 5);
    }

    private WebDriverWait wait;
    private By firstNameFieldLocator = By.cssSelector("#billing_first_name");
    private By lastNameFieldLocator = By.cssSelector("#billing_last_name");
    private By addressFieldLocator = By.cssSelector("#billing_address_1");
    private By postalCodeFieldLocator = By.cssSelector("#billing_postcode");
    private By cityFieldLocator = By.cssSelector("#billing_city");
    private By phoneFieldLocator = By.cssSelector("#billing_phone");
    private By emailFieldLocator = By.cssSelector("#billing_email");
    private By countryCodeArrowLocator = By.cssSelector(".select2-selection__arrow");
    private By countryListLocator = By.cssSelector("li[id*='VI']");
    private By loadingIcon = By.cssSelector(".blockOverlay");
    private By cardNumberField = By.cssSelector("[name='cardnumber']");
    private By expirationDateField = By.cssSelector("[name='exp-date']");
    private By cvcField = By.cssSelector("[name='cvc']");
    private By orderButton = By.cssSelector("#place_order");
    private By iframeListLocator = By.cssSelector(".__PrivateStripeElement>iframe");
    private By termsCheckboxLocator = By.cssSelector("#terms");
    private By billingStateLocator = By.cssSelector("input#billing_state");



    public CheckoutPage fillOutCheckoutForm(String email, String phone) {
        wait.until(ExpectedConditions.elementToBeClickable(firstNameFieldLocator)).sendKeys("Ola");
        wait.until(ExpectedConditions.elementToBeClickable(lastNameFieldLocator)).sendKeys("Nowak");
        wait.until(ExpectedConditions.elementToBeClickable(countryCodeArrowLocator)).click();
        wait.until(ExpectedConditions.elementToBeClickable(countryListLocator)).click();
        wait.until(ExpectedConditions.elementToBeClickable(addressFieldLocator)).click();
        wait.until(ExpectedConditions.elementToBeClickable(addressFieldLocator)).sendKeys("Kcyńska 3/63");
        wait.until(ExpectedConditions.elementToBeClickable(postalCodeFieldLocator)).sendKeys("80-001");
        wait.until(ExpectedConditions.elementToBeClickable(cityFieldLocator)).sendKeys("Gdynia");
        wait.until(ExpectedConditions.elementToBeClickable(billingStateLocator)).sendKeys("Pomorskie");
        wait.until(ExpectedConditions.elementToBeClickable(phoneFieldLocator)).sendKeys(phone);
        wait.until(ExpectedConditions.elementToBeClickable(emailFieldLocator)).sendKeys(email);
        return this;
    }

    public CheckoutPage fillOutCardData(boolean acceptTerms) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));
        List<WebElement> iframeList = driver.findElements(iframeListLocator);
        driver.switchTo().frame(iframeList.get(0));
        wait.until(ExpectedConditions.elementToBeClickable(cardNumberField)).sendKeys("4000000560000004");
        driver.switchTo().defaultContent();
        driver.switchTo().frame(iframeList.get(1));
        wait.until(ExpectedConditions.elementToBeClickable(expirationDateField)).sendKeys("0530");
        driver.switchTo().defaultContent();
        driver.switchTo().frame(iframeList.get(2));
        wait.until(ExpectedConditions.elementToBeClickable(cvcField)).sendKeys("530");
        driver.switchTo().defaultContent();
        if (acceptTerms) {
            driver.findElement(termsCheckboxLocator).click();
        }
        return this;
    }

    public void order() {
        driver.findElement(orderButton).click();
     }
}