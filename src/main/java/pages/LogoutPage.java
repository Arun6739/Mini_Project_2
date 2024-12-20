package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LogoutPage extends BasePage {

    @FindBy(id = "logout") 
    private WebElement logoutButton;

    public LogoutPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLogoutButtonVisible() {
        return new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(logoutButton)).isDisplayed();
    }

    public void clickLogoutButton() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(logoutButton)).click();
    }

}
