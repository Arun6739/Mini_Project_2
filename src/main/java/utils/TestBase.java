package utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;

//import pages.HomePage;

public class TestBase {
    protected static WebDriver driver;
   // private HomePage homePage;

    @BeforeClass
    public void setUp() {
        if (driver == null) {
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\pcs-pc\\Downloads\\chromedriver-win64\\chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.get("https://thinking-tester-contact-list.herokuapp.com/");
            /*
            String validEmail = "arunkumart315615@gmail.com";
            String validPassword = "Arun@2000";
            homePage = new HomePage(driver);

            // Perform login with valid credentials
            homePage.login(validEmail, validPassword);*/
        }
    }

    protected void captureScreenshot(String testName) {
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File destFile = new File("screenshots/" + testName + "_" + timestamp + ".png");
        try {
            if (!destFile.getParentFile().exists()) {
                destFile.getParentFile().mkdirs();
            }
            FileUtils.copyFile(srcFile, destFile);
            System.out.println("Screenshot saved to: " + destFile.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Failed to save screenshot: " + e.getMessage());
        }
    }

    
}
