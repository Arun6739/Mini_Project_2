package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.LogoutPage;
import utils.TestBase;

public class LogoutTest extends TestBase {

    private LogoutPage logoutPage;
    private HomePage homePage;
    
    @Test(priority = 1)
    public void testLogoutButtonVisible() {
    	logoutPage = new LogoutPage(driver);
        Assert.assertTrue(logoutPage.isLogoutButtonVisible(), "Logout button is not visible on the Contact List page.");
    }

    @Test(priority = 2)
    public void testLogoutRedirectsToLoginPage() {
    	logoutPage = new LogoutPage(driver);
    	homePage =new HomePage(driver);

        logoutPage.clickLogoutButton();

        Assert.assertTrue(homePage.isLoginSubmitButtonVisible(), "Failed to redirect to the login page after logging out.");
    }

}
