package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ContactPage extends BasePage {

    @FindBy(id = "add-contact")
    private WebElement addContactButton;

    @FindBy(id = "firstName") 
    private WebElement firstNameField;

    @FindBy(id = "lastName") 
    private WebElement lastNameField;

    @FindBy(id = "birthdate") 
    private WebElement dobField;

    @FindBy(id = "email") 
    private WebElement emailField;

    @FindBy(id = "phone") 
    private WebElement phoneField;

    @FindBy(id = "street1") 
    private WebElement address1Field;

    @FindBy(id = "street2") 
    private WebElement address2Field;

    @FindBy(id = "city") 
    private WebElement cityField;

    @FindBy(id = "stateProvince") 
    private WebElement stateField;

    @FindBy(id = "postalCode") 
    private WebElement postalCodeField;

    @FindBy(id = "country") 
    private WebElement countryField;

    @FindBy(id = "submit") 
    private WebElement saveContactButton;

    @FindBy(css = ".error-message") 
    private WebElement errorMessage;

    @FindBy(id = "error") 
    private WebElement errorSpan;

    @FindBy(css = "table.contactTable tr.contactTableBodyRow")
    private List<WebElement> contactRows;

    @FindBy(id = "edit-contact")
    private WebElement editContactButton;

    @FindBy(id = "return")
    private WebElement returnButton;

    private WebDriverWait wait;

    public ContactPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, 10); 
    }

    public boolean isAddContactButtonVisible() {
        return wait.until(ExpectedConditions.visibilityOf(addContactButton)).isDisplayed();
    }

    public void clickAddContactButton() {
        wait.until(ExpectedConditions.elementToBeClickable(addContactButton)).click();
    }

    public void enterContactDetails(String firstName, String lastName, String dob, String email, String phone,
                                     String address1, String address2, String city, String state, String postalCode, String country) {
        if (!firstName.isEmpty()) updateField(firstNameField, firstName);
        if (!lastName.isEmpty()) updateField(lastNameField, lastName);
        if (!dob.isEmpty()) updateField(dobField, dob);
        if (!email.isEmpty()) updateField(emailField, email);
        if (!phone.isEmpty()) updateField(phoneField, phone);
        if (!address1.isEmpty()) updateField(address1Field, address1);
        if (!address2.isEmpty()) updateField(address2Field, address2);
        if (!city.isEmpty()) updateField(cityField, city);
        if (!state.isEmpty()) updateField(stateField, state);
        if (!postalCode.isEmpty()) updateField(postalCodeField, postalCode);
        if (!country.isEmpty()) updateField(countryField, country);
    }

    private void updateField(WebElement field, String value) {
        wait.until(ExpectedConditions.visibilityOf(field)).click();
        safeSleep(1000);
        field.sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        field.sendKeys(value);
    }

    public void clickSaveContactButton() {
        wait.until(ExpectedConditions.elementToBeClickable(saveContactButton)).click();
    }

    public boolean isErrorMessageDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(errorSpan)).isDisplayed();
    }

    public boolean isContactDisplayed() {
        try {
            WebElement contactListHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='Contact List']")));
            return contactListHeader.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public List<WebElement> getContactRows() {
        return wait.until(ExpectedConditions.visibilityOfAllElements(contactRows));
    }

    public List<String> getAllContactLastNames() {
        List<String> lastNames = new ArrayList<>();

        System.out.println("Fetching last names from the contact table...");

        List<WebElement> rows = driver.findElements(By.xpath("//*[@id='myTable']//tr[contains(@class, 'contactTableBodyRow')]"));

        for (WebElement row : rows) {
            try {
                WebElement nameCell = row.findElement(By.xpath("./td[2]")); 
                String fullName = nameCell.getText();

                String[] nameParts = fullName.split(" ");

                if (nameParts.length > 1) {
                    lastNames.add(nameParts[1]); 
                } else {
                    System.out.println("Skipping row with incomplete name: " + fullName);
                }
            } catch (Exception e) {
                
                System.err.println("Error while fetching last name: " + e.getMessage());
            }
        }

        
        System.out.println("Unsorted Last Names: " + lastNames);

        Collections.sort(lastNames);

        System.out.println("Sorted Last Names: " + lastNames);

        return lastNames;
    }


    public List<String> getAllPhoneNumbers() {
        List<String> phoneNumbers = new ArrayList<>();
        for (WebElement row : contactRows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            if (cells.size() > 3) {
                phoneNumbers.add(cells.get(3).getText()); 
            }
        }
        return phoneNumbers;
    }

    public void clickContactRow() {
        wait.until(ExpectedConditions.visibilityOfAllElements(contactRows));
        WebElement contactRow = driver.findElement(By.xpath("/html/body/div/div/table/tr[1]"));
        wait.until(ExpectedConditions.elementToBeClickable(contactRow)).click();
    }

    public boolean isEditContactButtonVisible() {
        return wait.until(ExpectedConditions.visibilityOf(editContactButton)).isDisplayed();
    }

    private void safeSleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); 
            System.err.println("Sleep was interrupted: " + e.getMessage());
        }
    }

    public void editContactDetails(String firstName, String lastName, String email, String phone) {

    	wait.until(ExpectedConditions.elementToBeClickable(editContactButton)).click();

        if (!firstName.isEmpty()) updateField(firstNameField, firstName);
        if (!lastName.isEmpty()) updateField(lastNameField, lastName);
        if (!email.isEmpty()) updateField(emailField, email);
        if (!phone.isEmpty()) updateField(phoneField, phone);

        wait.until(ExpectedConditions.elementToBeClickable(saveContactButton)).click();

    }
    
    @FindBy(xpath = "//*[@id=\"firstName\"]")
    private WebElement firstName;
    
    @FindBy(xpath = "//*[@id=\"lastName\"]")
    private WebElement lastName;
    
    @FindBy(xpath = "//*[@id=\"email\"]")
    private WebElement email;
    
    @FindBy(xpath = "//*[@id=\"phone\"]")
    private WebElement phonenumber;
    
    
    public String getFirstName() {
        return getFieldValue(By.id("firstName"));
    }

    public String getLastName() {
        return getFieldValue(By.id("lastName"));
    }

    public String getEmail() {
        return getFieldValue(By.id("email"));
    }

    public String getPhone() {
        return getFieldValue(By.id("phone"));
    }
    
    public void clickReturnToContactList() {
    	safeSleep(2000);
     wait.until(ExpectedConditions.elementToBeClickable(returnButton)).click();
    }

 // Utility method to fetch a field's value with wait and retry
    public String getFieldValue(By fieldLocator) {
        try {

        	WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(fieldLocator));
            return field.getAttribute("value");
        } catch (org.openqa.selenium.StaleElementReferenceException e) {
            System.err.println("Stale element reference, retrying to fetch the field value...");
          
            WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(fieldLocator));
            return field.getAttribute("value");
        } catch (Exception e) {
            System.err.println("Unable to fetch field value: " + e.getMessage());
            return ""; 
        }
    }

    @FindBy(id="cancel")
    private WebElement cancel;
    
	public void clickCancel() {
	     wait.until(ExpectedConditions.elementToBeClickable(cancel)).click();

	}
}
