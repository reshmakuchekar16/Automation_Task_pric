package jAvascript;

import java.time.Duration;
import java.util.regex.Pattern;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OtpTesting {

    public static void main(String[] args) throws InterruptedException {
        // Set up WebDriver and navigate to the page
        System.setProperty("webdriver.chrome.driver", "F:\\Learning\\chromedriver-win64\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://app.thepric.com/");  // Replace with your actual URL

        // Wait for the mobile number input field to appear
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement mobileField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("tel")));

        // Test Case 1: Validate mobile number input
        validateMobileNumber(driver, mobileField, "12345");  // Invalid number (too short)
        validateMobileNumber(driver, mobileField, "98765abc12");  // Invalid number (contains characters)
        validateMobileNumber(driver, mobileField, "9876543210");  // Valid number

        // Test Case 2: Click "Send OTP" button
        WebElement sendOtpButton = driver.findElement(By.xpath("//button[text()='Send OTP']"));
        if (sendOtpButton.isEnabled()) {
            System.out.println("Send OTP button is clickable.");
            sendOtpButton.click();

            // Optionally handle OTP input or further steps after clicking the "Send OTP" button
            Thread.sleep(3000);  // Wait for OTP input (optional)

            // Handle OTP input (if relevant) here
            handleOtpInput(driver, "123456");  // Example random OTP, replace with dynamic handling
        } else {
            System.out.println("Send OTP button is not clickable.");
        }

        // Close the browser
        driver.quit();
    }

    // Function to validate mobile number
    public static void validateMobileNumber(WebDriver driver, WebElement mobileField, String mobileNumber) {
        // Clear and enter mobile number
        mobileField.clear();
        mobileField.sendKeys(mobileNumber);

        // Check if the entered number is valid
        if (isValidMobile(mobileNumber)) {
            System.out.println("Mobile number " + mobileNumber + " is valid.");
        } else {
            System.out.println("Mobile number " + mobileNumber + " is invalid.");
            handleValidationError(driver);
        }
    }

    // Function to check if the mobile number is valid (10 digits, no letters)
    public static boolean isValidMobile(String mobile) {
        return mobile.matches("\\d{10}");  // Regex for exactly 10 digits
    }

    // Function to handle validation error (if there is a UI indication)
    public static void handleValidationError(WebDriver driver) {
        // You can implement logic to detect validation messages on the page
        try {
            WebElement validationMessage = driver.findElement(By.xpath("//*[contains(text(),'Invalid Mobile')]"));  // Example message
            if (validationMessage.isDisplayed()) {
                System.out.println("Validation error message is displayed: " + validationMessage.getText());
            }
        } catch (NoSuchElementException e) {
            System.out.println("No validation error message found.");
        }
    }

    // Function to handle OTP input (if applicable)
    public static void handleOtpInput(WebDriver driver, String otp) {
        // Wait for OTP input field to appear (if there is one)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement otpField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("otp")));  // Adjust as needed

        // Enter OTP and proceed
        otpField.clear();
        otpField.sendKeys(otp);
        System.out.println("Entered OTP: " + otp);

        // Optional: Submit OTP
        WebElement submitOtpButton = driver.findElement(By.xpath("//button[text()='Submit']"));  // Adjust as needed
        if (submitOtpButton.isEnabled()) {
            submitOtpButton.click();
            System.out.println("OTP submitted.");
        }
    }
}
