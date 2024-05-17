import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.Assert;

import java.time.Duration;

public class OnlineBookStoreApplication {
    public WebDriver driver;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "D:\SELENIUM\SELENIUM_SUITES\chromedriver-win32\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.store.bookrivers.com/");

        WebElement registorBtn = driver.findElement(By.xpath("//a[text()='Login / Register']"));
        registorBtn.click();

    }

    @Test(priority = 1)
    public void testUserRegistration() {
        // Navigate to registration page
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Login / Register']")));
        driver.findElement(By.linkText("Register")).click();

        // Fill in registration form
        driver.findElement(By.id("username")).sendKeys("testuser");
        driver.findElement(By.id("email")).sendKeys("testuser@example.com");
        driver.findElement(By.id("password")).sendKeys("testpassword");
        driver.findElement(By.id("confirmPassword")).sendKeys("testpassword");
        driver.findElement(By.id("registerButton")).click();

        // Verify successful registration
        WebElement successMessage = driver.findElement(By.className("success-message"));
        Assert.assertTrue(successMessage.isDisplayed());
    }

    @Test(priority = 2)
    public void testUserLogin() {
        // Navigate to login page
        driver.findElement(By.linkText("Login")).click();

        // Fill in login form
        driver.findElement(By.id("username")).sendKeys("testuser");
        driver.findElement(By.id("password")).sendKeys("testpassword");
        driver.findElement(By.id("loginButton")).click();

        // Verify successful login
        WebElement welcomeMessage = driver.findElement(By.className("welcome-message"));
        Assert.assertTrue(welcomeMessage.isDisplayed());
    }

    @Test(priority = 3)
    public void testPasswordReset() {
        // Navigate to password reset page
        driver.findElement(By.linkText("Forgot Password?")).click();

        // Fill in email for password reset
        driver.findElement(By.id("email")).sendKeys("testuser@example.com");
        driver.findElement(By.id("resetPasswordButton")).click();

        // Verify password reset confirmation
        WebElement resetConfirmation = driver.findElement(By.className("reset-confirmation"));
        Assert.assertTrue(resetConfirmation.isDisplayed());
    }

    // Similarly, you can implement tests for other features like catalog, shopping cart, and checkout

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

}
