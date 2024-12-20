package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {

    @FindBy(id = "signup")
    private WebElement signupButton;

    @FindBy(id = "email")
    private WebElement emailField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "submit")
    private WebElement loginSubmitButton;

    @FindBy(id = "logout")
    private WebElement logoutButton;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public boolean isSignupButtonVisible() {
        return signupButton.isDisplayed();
    }

    public boolean isSignupButtonClickable() {
        return signupButton.isEnabled();
    }

    public void clickSignupButton() {
        signupButton.click();
    }

    public void enterEmail(String email) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(emailField)).clear();
        emailField.sendKeys(email);
    }

    public void enterPassword(String password) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(passwordField)).clear();
        passwordField.sendKeys(password);
    }

    public boolean isLoginSubmitButtonVisible() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        return wait.until(ExpectedConditions.visibilityOf(loginSubmitButton)).isDisplayed();
    }

    public boolean isLoginSubmitButtonClickable() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        return wait.until(ExpectedConditions.elementToBeClickable(loginSubmitButton)).isEnabled();
    }

    public void clickLoginSubmitButton() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(loginSubmitButton)).click();
    }

    public void clickLogoutButton() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton)).click();
    }

    public void login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLoginSubmitButton();
    }

    public boolean isLoginSuccessful() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            return wait.until(ExpectedConditions.titleIs("My Contacts"));
        } catch (Exception e) {
            return false;
        }
    }
}
