package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ContactPage;
import utils.TestBase;

public class ContactEditingTest extends TestBase {
    private ContactPage contactPage;
    
    @Test(priority = 1)
    public void testPageRedirectsOnContactClick() {
    	
        contactPage = new ContactPage(driver);

        contactPage.clickContactRow(); 
        
        Assert.assertTrue(contactPage.isEditContactButtonVisible(),
                "Page did not redirect to the contact details page.");
    }
    

    @Test(priority = 2)
    public void testEditExistingContactDetails() {
    	
        contactPage = new ContactPage(driver);

        contactPage.editContactDetails("Jhon", "Adam", "Jhon.adam@example.com", "9876543211");

        
        Assert.assertEquals(contactPage.getFirstName(), "Jhon", "First name not updated.");
        Assert.assertEquals(contactPage.getLastName(), "Adam", "Last name not updated.");
        Assert.assertEquals(contactPage.getEmail(), "Jhon.adam@example.com", "Email not updated.");
        Assert.assertEquals(contactPage.getPhone(), "9876543211", "Phone number not updated.");
    }

    

    @Test(priority = 3)
    public void testUneditedFieldsAreUnaffected() {
    	
        contactPage = new ContactPage(driver);

        contactPage.clickReturnToContactList();
        contactPage.clickContactRow();

        contactPage.editContactDetails("Jane", "", "", ""); 

        
        Assert.assertEquals(contactPage.getFirstName(), "Jane", "First name not updated.");
        Assert.assertEquals(contactPage.getLastName(), "Adam", "Last name was unexpectedly changed.");
        Assert.assertEquals(contactPage.getEmail(), "jhon.adam@example.com", "Email was unexpectedly changed.");
        Assert.assertEquals(contactPage.getPhone(), "9876543211", "Phone number was unexpectedly changed.");
    }

    @Test(priority = 4)
    public void testSavingEmptyFields() {
    	
        contactPage = new ContactPage(driver);

        contactPage.clickReturnToContactList();

        contactPage.clickContactRow(); 

        contactPage.editContactDetails("", "", "", ""); 

        
        Assert.assertTrue(contactPage.isErrorMessageDisplayed(), "No error message displayed for empty fields."); 
    
        contactPage.clickCancel();
        contactPage.clickReturnToContactList();

    }
}
