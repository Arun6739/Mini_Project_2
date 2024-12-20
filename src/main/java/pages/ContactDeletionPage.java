package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ContactDeletionPage extends BasePage {

    @FindBy(css = ".contactTableBodyRow") 
    private List<WebElement> contactRows;

    @FindBy(id = "logout") 
    private WebElement logoutButton;

    public ContactDeletionPage(WebDriver driver) {
        super(driver);
    }

    public boolean isDeleteAlertDisplayed() {
        try {
            Alert alert = new WebDriverWait(driver, 10).until(ExpectedConditions.alertIsPresent());
            
            return alert != null;
        } catch (Exception e) {
            return false;
        }
    }

    public void confirmDeletion() {
        Alert alert = new WebDriverWait(driver, 10).until(ExpectedConditions.alertIsPresent());
        alert.accept(); 
    }

    public void cancelDeletion() {
        Alert alert = new WebDriverWait(driver, 10).until(ExpectedConditions.alertIsPresent());
        alert.dismiss(); 
    }

    @FindBy (id ="delete")
    private WebElement deleteButton;
    
    public void deleteContact(int rowIndex) {
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(deleteButton)).click();
    }

    public int getContactCount() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfAllElements(contactRows));
        return contactRows.size();
    }
}
