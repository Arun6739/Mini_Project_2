package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ContactPage;
import utils.TestBase;

public class ContactAdditionTest extends TestBase {
    private ContactPage contactPage;

    @Test(priority = 1)
    public void testAddContactButtonVisibility() {
        contactPage = new ContactPage(driver);
        Assert.assertTrue(contactPage.isAddContactButtonVisible(), "Add Contact button is not visible.");
    }

    @Test(priority = 2)
    public void testAddContactWithAllDetails() {
        String firstName = "Tom";
        String lastName = "Holland";
        String dob = "1990-05-31";
        String email = "Tom.doe@example.com";
        String phone = "1234567890";
        String address1 = "123 Spidy St";
        String address2 = "Stark Tower";
        String city = "New York";
        String state = "NY";
        String postalCode = "10001";
        String country = "USA";

        contactPage.clickAddContactButton();
        contactPage.enterContactDetails(firstName, lastName, dob, email, phone, address1, address2, city, state, postalCode, country);
        contactPage.clickSaveContactButton();

        Assert.assertTrue(contactPage.isContactDisplayed(), "Contact List header is not displayed, indicating failure.");
    }

    @Test(priority = 3)
    public void testAddContactWithRequiredDetails() {
        contactPage.clickAddContactButton();
        contactPage.enterContactDetails("Tony", "Jerry", "", "", "", "", "", "", "", "", "");
        contactPage.clickSaveContactButton();
        Assert.assertTrue(contactPage.isContactDisplayed(), "Contact List header is not displayed.");
    }

    @Test(priority = 4)
    public void testInvalidDateOfBirth() {
        contactPage.clickAddContactButton();
        contactPage.enterContactDetails("Jane", "Adam", "31-01-2000", "", "", "", "", "", "", "", "");
        contactPage.clickSaveContactButton();
        
        
        Assert.assertTrue(contactPage.isErrorMessageDisplayed(), "Error message for invalid date of birth was not displayed.");
        contactPage.clickCancel();
    }
}
