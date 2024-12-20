package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.HomePage;
import utils.TestBase;

public class LoginTest extends TestBase {
    private HomePage homePage;

    @BeforeClass
    public void setup() {
        homePage = new HomePage(driver);
    }

    @Test(priority = 1)
    public void testLoginSubmitButtonVisible() {
        Assert.assertTrue(homePage.isLoginSubmitButtonVisible(),
                "Login Submit button is not visible on the homepage.");
    }

    @Test(priority = 2)
    public void testLoginSubmitButtonClickable() {
        Assert.assertTrue(homePage.isLoginSubmitButtonClickable(),
                "Login Submit button is not clickable.");
    }
    @Test(priority = 3)
    public void testInvalidLogin() {
        String invalidEmail = "invalidUser@example.com";
        String invalidPassword = "wrongPassword";

        homePage.login(invalidEmail, invalidPassword);

        Assert.assertFalse(homePage.isLoginSuccessful(),
                "Unexpectedly logged in with invalid credentials.");
    }
    @Test(priority = 4)
    public void testValidLogin() {
        String validEmail = "arunkumart315615@gmail.com";
        String validPassword = "Arun@2000";

        homePage.login(validEmail, validPassword);

        Assert.assertTrue(homePage.isLoginSuccessful(),
                "Expected to see the Contact List page after successful login.");
    }

    
}
