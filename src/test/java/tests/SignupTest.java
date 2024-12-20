package tests;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.HomePage;
import utils.TestBase;

public class SignupTest extends TestBase {
    private HomePage homePage;

    @BeforeClass
    public void setup() {
        homePage = new HomePage(driver);
    }

    @Test(priority = 1)
    public void testSignupButtonVisible() {
        try {
            Assert.assertTrue(homePage.isSignupButtonVisible(),
                    "Sign Up button is not visible.");
        } catch (AssertionError e) {
            captureScreenshot("testSignupButtonVisible");
            throw e;
        }
    }

    @Test(priority = 2)
    public void testSignupButtonClickable() {
        Assert.assertTrue(homePage.isSignupButtonClickable(),
                "Sign Up button is not clickable.");
    }

    @Test(priority = 3)
    public void testSignupButtonClick() {
        homePage.clickSignupButton();
        String currentUrl = driver.getCurrentUrl();
        String expectedUrl = "https://thinking-tester-contact-list.herokuapp.com/addUser";
        Assert.assertTrue(currentUrl.contains(expectedUrl),
                "Failed to navigate to Add User Page. Current URL: " + currentUrl);
    }
    @AfterClass
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
