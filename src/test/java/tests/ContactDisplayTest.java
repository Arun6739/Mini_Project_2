package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pages.ContactPage;
import utils.TestBase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ContactDisplayTest extends TestBase {
    private ContactPage contactPage;

    @Test(priority = 1)
    public void testContactDetailsDisplayedCorrectly() {
        contactPage = new ContactPage(driver);

        String expectedFirstName = "Tom";
        String expectedLastName = "Holland";
        boolean isContactFound = false;

        WebElement firstRow = driver.findElement(By.xpath("//*[@id=\"myTable\"]/tr[1]"));

        List<WebElement> cells = firstRow.findElements(By.tagName("td"));

        if (cells.size() >= 7) {
            
            String name = cells.get(1).getText(); 
            String birthdate = cells.get(2).getText(); 
            String email = cells.get(3).getText(); 
            String phone = cells.get(4).getText(); 
            String address = cells.get(5).getText(); 
            String cityStatePostalCode = cells.get(6).getText(); 
            String country = cells.get(7).getText(); 

            if (name.equals(expectedFirstName + " " + expectedLastName)) {
                isContactFound = true;
                Assert.assertEquals(birthdate, "1990-05-31", "Birthdate does not match.");
                Assert.assertEquals(email, "tom.doe@example.com", "Email does not match.");
                Assert.assertEquals(phone, "1234567890", "Phone number does not match.");
                Assert.assertEquals(address, "123 Spidy St Stark Tower", "Address does not match.");
                Assert.assertEquals(cityStatePostalCode, "New York NY 10001", "City/State/Postcode does not match.");
                Assert.assertEquals(country, "USA", "Country does not match.");
            }
        } else {
            System.out.println("Row does not have enough cells.");
        }

        Assert.assertTrue(isContactFound, "Contact with name '" + expectedFirstName + " " + expectedLastName + "' is not displayed correctly.");
    }


    @Test(priority = 2)
    public void testContactsAlphabeticalOrderByLastName() {
    	
        contactPage = new ContactPage(driver);

        List<String> actualOrder = contactPage.getAllContactLastNames();

        List<String> expectedOrder = new ArrayList<>(actualOrder);
        Collections.sort(expectedOrder);

        System.out.println("Actual Order: " + actualOrder);
        System.out.println("Expected Order: " + expectedOrder);

        Assert.assertEquals(actualOrder, expectedOrder, "Contacts are not sorted alphabetically by last name.");
    }



   @Test(priority = 3)
    public void testPhoneNumberWithExtensions() {
        List<String> phoneNumbers = contactPage.getAllPhoneNumbers();
        for (String phone : phoneNumbers) {
            Assert.assertTrue(phone.startsWith("+91") || phone.matches("\\d{10}"),
                "Phone number does not have the correct extension: " + phone);
        }
    }

    @AfterMethod
    public void takeScreenshotOnFailure(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            captureScreenshot(result.getName());
        }
    }
}
