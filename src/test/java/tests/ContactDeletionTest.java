package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ContactDeletionPage;
import pages.ContactPage;
import utils.TestBase;

public class ContactDeletionTest extends TestBase {

    private ContactDeletionPage contactDeletionPage;

    private ContactPage contactPage;
    
    @Test(priority = 1)
    public void testDeleteContactPopsUpAlert() {
    	contactDeletionPage = new ContactDeletionPage(driver);
        int initialContactCount = contactDeletionPage.getContactCount();
       System.out.println(+initialContactCount);
        contactPage = new ContactPage(driver);

        contactPage.clickContactRow(); 

        contactDeletionPage.deleteContact(0);

        
        Assert.assertTrue(contactDeletionPage.isDeleteAlertDisplayed(), "Alert for deletion confirmation is not displayed.");

        contactDeletionPage.cancelDeletion();
        contactPage.clickReturnToContactList();

        int contactCountAfterCancel = contactDeletionPage.getContactCount();
        Assert.assertEquals(contactCountAfterCancel, initialContactCount, "Contact count changed after canceling deletion.");

    }
   
    @Test(priority = 2)
    public void testDeleteSingleContact() {

    	contactPage = new ContactPage(driver);
    	contactDeletionPage = new ContactDeletionPage(driver);

        int initialContactCount = contactDeletionPage.getContactCount();


        contactPage.clickContactRow(); 

        
        contactDeletionPage.deleteContact(0);
        contactDeletionPage.confirmDeletion();

        
        int contactCountAfterDeletion = contactDeletionPage.getContactCount();
        Assert.assertEquals(contactCountAfterDeletion, initialContactCount - 1, "Contact count did not decrease after deletion.");
    }

    @Test(priority = 3)
    public void testDeletionDoesNotAffectOtherContacts() {
    	
    	contactPage = new ContactPage(driver);
    	contactDeletionPage = new ContactDeletionPage(driver);

        int initialContactCount = contactDeletionPage.getContactCount();

        contactPage.clickContactRow();

        
        contactDeletionPage.deleteContact(0);
        contactDeletionPage.confirmDeletion();

        
        int remainingContacts = contactDeletionPage.getContactCount();
        Assert.assertEquals(remainingContacts, initialContactCount - 1, "Other contacts were affected by deletion.");
    }

    
}
