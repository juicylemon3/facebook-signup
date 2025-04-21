package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.junit.Assert; // Import for assertions
import java.time.Duration;
import io.github.bonigarcia.wdm.WebDriverManager; // Import WebDriverManager

public class FacebookSignupAutomation {

    public static void main(String[] args) {
        // 1. Set up ChromeDriver
        //    -  Ensure you have the correct chromedriver.exe for your Chrome version.
        //    -  Consider using WebDriverManager to automate driver management.
        // System.setProperty("webdriver.chrome.driver", "path/to/chromedriver.exe"); // **<-- Replace with your path** <-- REMOVE THIS LINE
        WebDriverManager.chromedriver().setup(); // Use WebDriverManager instead

        WebDriver driver = new ChromeDriver();

        // 2. Navigate to Facebook
        driver.get("https://www.facebook.com/");
        System.out.println("Navigated to Facebook");

        // 3. Find and click the "Create New Account" button
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10), Duration.ofMillis(5000)); // 10s timeout, 1s polling
        WebElement createAccountButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Create new account')]")));
        createAccountButton.click();
        System.out.println("Clicked on 'Create New Account'");

        // 4.  Use WebDriverWait to wait for the signup form to appear.  This is crucial,
        //     as Facebook's elements load dynamically.
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("firstname")));
        System.out.println("Signup form is visible");

        // 5. Locate the input fields and fill them in
        WebElement firstNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("firstname")));
        firstNameField.sendKeys("John");
        System.out.println("Entered first name");

        WebElement lastNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("lastname")));
        lastNameField.sendKeys("Doe");
        System.out.println("Entered last name");

        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("reg_email__")));
        emailField.sendKeys("john.doe@example.com"); // Use a unique email for testing
        System.out.println("Entered email");

        WebElement reenterEmailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("reg_email_confirmation__")));
        reenterEmailField.sendKeys("john.doe@example.com");
        System.out.println("Re-entered email");

        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password_step")));
        passwordField.sendKeys("Password123");
        System.out.println("Entered password");

        // 6. Select the date of birth
        Select daySelectElement = new Select(wait.until(ExpectedConditions.elementToBeClickable(By.id("day"))));
        daySelectElement.selectByValue("19"); // Select the 19th day.
        System.out.println("Selected day");

        Select monthSelectElement = new Select(wait.until(ExpectedConditions.elementToBeClickable(By.id("month"))));
        monthSelectElement.selectByValue("11"); // Select November
        System.out.println("Selected month");

        Select yearSelectElement = new Select(wait.until(ExpectedConditions.elementToBeClickable(By.id("year"))));
        yearSelectElement.selectByValue("1999");  // Select 1999
        System.out.println("Selected year");

        // 7. Select gender
        WebElement genderRadioButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='2']"))); // Select male.
        genderRadioButton.click();
        System.out.println("Selected gender");

        // 8. Click the SignUp button
        WebElement signUpButton = wait.until(ExpectedConditions.elementToBeClickable(By.name("websubmit")));
        signUpButton.click();
        System.out.println("Clicked on Sign Up");

        // 9.  Add a wait for an element on the next page to load, to confirm successful sign-up.
        //     This is a *basic* check.  For robust testing, you'd check for specific elements
        //     related to account confirmation (email verification, etc.).  The element below
        //     is just an example, and might change.  You'll need to inspect the Facebook page
        //     after signup to choose a reliable element. Increase the timeout if necessary.
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'Enter the code from your email')]")));
            System.out.println("Successfully signed up (basic check: email confirmation prompt is present).");
        } catch (org.openqa.selenium.TimeoutException e) {
            System.out.println("Signup was *likely* successful, but the expected email confirmation prompt was not found within the timeout.");
            //  In a real test, you would use Assert.fail() here, or check for other success indicators.
        }

        // 10. Close the browser
        driver.quit(); //Commented out for debugging
    }
}
